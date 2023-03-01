package com.example.BookHub.Security;

import com.example.BookHub.Security.OAuth2.CustomOAuth2UserService;
import com.example.BookHub.Security.OAuth2.OAuth2LoginFailureHandler;
import com.example.BookHub.Security.OAuth2.OAuth2LoginSuccessHandler;
import com.example.BookHub.User.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final CustomOAuth2UserService customOAuth2UserService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
         .cors().disable()
         .httpBasic().disable()
         .formLogin().loginPage("/signin")
         .and()
         .authorizeRequests()
         .anyRequest().permitAll()
         .and()
         .logout().logoutUrl("/logout")
         .logoutSuccessUrl("/")
         .and()
         .oauth2Login()
         .userInfoEndpoint()
         .userService(customOAuth2UserService)
         .and()
         .defaultSuccessUrl("/folder/list");
        return http.build();
    }
}
