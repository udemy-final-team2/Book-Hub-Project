package com.example.BookHub.Security.OAuth2;

import com.example.BookHub.Security.Auth.AuthenticationResponse;
import com.example.BookHub.Security.JwtService;
import com.example.BookHub.User.Role;
import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            Optional<UserDTO> optionalUser = userRepository.findByEmail(oAuth2User.getEmail());
            UserDTO user;
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                user = UserDTO.builder()
                 .name(oAuth2User.getName())
                 .email(oAuth2User.getEmail())
                 .role(oAuth2User.getRole())
                 .build();
                userRepository.insertUser(user);
            }
            String accessToken = jwtService.generateToken(user);
            AuthenticationResponse.builder().token(accessToken).build();
            try {
                response.sendRedirect("/docs");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("successhandler 오류");
        }
    }
}
