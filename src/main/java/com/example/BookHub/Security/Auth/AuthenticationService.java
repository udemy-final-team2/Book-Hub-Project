package com.example.BookHub.Security.Auth;

import com.example.BookHub.Security.JwtService;
import com.example.BookHub.Security.Token.TokenDTO;
import com.example.BookHub.Security.Token.TokenRepository;
import com.example.BookHub.Security.Token.TokenType;
import com.example.BookHub.User.Role;
import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        String name = (request.getName() != null) ? request.getName() : "";
        var user = UserDTO.builder()
         .email(request.getEmail())
         .password(encodedPassword)
         .name(name)
         .role(Role.USER)
         .build();
        repository.insertUser(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
         .token(jwtToken)
         .build();
    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(
          request.getEmail(),
          request.getPassword()
         )
        );
        var user = repository.findByEmail(request.getEmail())
         .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
         .token(jwtToken)
         .build();
    }
    public void saveUserToken(UserDTO user, String jwtToken) {
        var token = TokenDTO.builder()
         .userId(user.getId())
         .token(jwtToken)
         .tokenType(TokenType.BEARER)
         .expired(false)
         .revoked(false)
         .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(UserDTO userDTO) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(userDTO.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
