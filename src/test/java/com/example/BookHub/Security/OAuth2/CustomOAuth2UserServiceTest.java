package com.example.BookHub.Security.OAuth2;

import com.example.BookHub.Security.OAuth2.CustomOAuth2UserService;
import com.example.BookHub.Security.OAuth2.OAuthAttributes;
import com.example.BookHub.Security.OAuth2.SocialType;
import com.example.BookHub.User.Role;
import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserRepository;
import com.example.BookHub.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomOAuth2UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CustomOAuth2UserService customOAuth2UserService;
    @Mock
    private UserService userService;

    @Test
    public void loadUser_ShouldInsertUserIntoDatabaseAndReturnOAuth2User_WhenUserLogsInForTheFirstTime() {
        // Given
        String registrationId = "naver";
        String userNameAttributeName = "id";
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("id", "testuser123");
        attributes.put("name", "Test User");
        attributes.put("email", "testuser123@example.com");

        OAuthAttributes extractAttributes = OAuthAttributes.of(SocialType.NAVER, userNameAttributeName, attributes);

        // When
        UserDTO createdUser = customOAuth2UserService.getUser(extractAttributes, SocialType.NAVER);

        // Then
        assertNotNull(createdUser);
    }
}
