package com.example.BookHub.Security;

import com.example.BookHub.Security.OAuth2.CustomOAuth2User;
import com.example.BookHub.User.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "24432646294A404E635266546A576E5A7234753778214125442A472D4B615064";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public Long extractUserId(String token) {
        return extractClaim(token, claims -> Long.parseLong(String.valueOf(claims.get("id"))));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDTO userDTO) {
        return generateToken(new HashMap<>(), userDTO);
    }

    public String generateToken(
     Map<String, Object> extraClaims,
     UserDTO userDTO
    ) {
        return Jwts
         .builder()
         .setClaims(extraClaims)
         .claim("id", userDTO.getId())
         .claim("name", userDTO.getName())
         .claim("role", userDTO.getRole())
         .setSubject(userDTO.getUsername())
         .setIssuedAt(new Date(System.currentTimeMillis()))
         .setExpiration(new Date(System.currentTimeMillis() + 1000 * 24 * 60))
         .signWith(getSignInKey(), SignatureAlgorithm.HS256)
         .compact();
    }

    public boolean isTokenValid(String token, UserDTO userDTO) {
        final String username = extractUsername(token);
        return (username.equals(userDTO.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
         .parserBuilder()
         .setSigningKey(getSignInKey())
         .build()
         .parseClaimsJws(token)
         .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
