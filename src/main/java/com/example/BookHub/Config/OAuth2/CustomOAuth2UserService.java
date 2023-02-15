package com.example.BookHub.Config.OAuth2;

import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements org.springframework.security.oauth2.client.userinfo.OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        String socialName = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName =
                userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(socialName, userNameAttributeName, oAuth2User.getAttributes());

        UserDTO userDTO =  saveOrUpdate(attributes, socialName);

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(userDTO.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private UserDTO saveOrUpdate(OAuthAttributes attributes, String socialName) {

        UserDTO user = userService.findUserByEmailAndSocialName(attributes.getEmail(), socialName)
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity(socialName));

        return userService.insertUserBySocial(user);
    }
}
