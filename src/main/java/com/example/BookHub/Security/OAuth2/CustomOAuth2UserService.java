package com.example.BookHub.Security.OAuth2;

import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserRepository;
import com.example.BookHub.User.UserService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;

    private static final String NAVER = "naver";
    private static final String KAKAO = "kakao";

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);


        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        SocialType socialType = getSocialType(registrationId);
        String userNameAttributeName =
         userRequest.getClientRegistration()
          .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        OAuthAttributes extractAttributes = OAuthAttributes.of(socialType, userNameAttributeName, attributes);
        UserDTO createdUser = getUser(extractAttributes, socialType);
        return new CustomOAuth2User(
         Collections.singleton(new SimpleGrantedAuthority(createdUser.getRole().toString())),
         attributes,
         extractAttributes.getNameAttributeKey(),
         createdUser.getId(),
         createdUser.getEmail(),
         createdUser.getName(),
         createdUser.getRole()
        );
    }

    private SocialType getSocialType(String registrationId) {
        if (NAVER.equals(registrationId)) {
            return SocialType.NAVER;
        }
        if (KAKAO.equals(registrationId)) {
            return SocialType.KAKAO;
        }
        return SocialType.GOOGLE;
    }

    public UserDTO getUser(OAuthAttributes attributes, SocialType socialType) {
        UserDTO findUser = userService.findBySocialId(attributes.getOauth2UserInfo().getId());

        if (findUser == null) {
            return insertUser(attributes, socialType);
        }
        return findUser;
    }

    private UserDTO insertUser(OAuthAttributes attributes, SocialType socialType) {
        UserDTO insertedUser = attributes.toEntity(socialType, attributes.getOauth2UserInfo());
        Long userId = userService.insertUser(insertedUser);
        return userService.findBySocialId(insertedUser.getSocialId());
    }

    public String getUserId(Authentication authentication) {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String provider = oauthToken.getAuthorizedClientRegistrationId();
        OAuth2User oauthUser = oauthToken.getPrincipal();
        Map<String, Object> userAttributes = oauthUser.getAttributes();
        String userId = null;
        if ("google".equals(provider)) {
            userId = userAttributes.get("sub").toString();
        } else if ("naver".equals(provider)) {
            Object response = userAttributes.get("response");
            String getId = response.toString();
            Pattern pattern = Pattern.compile("id=([^,}\\n]+)");
            Matcher matcher = pattern.matcher(getId);
            if (matcher.find()) {
                userId = matcher.group(1);
            }
        } else if ("kakao".equals(provider)) {
            userId = userAttributes.get("id").toString();
        }

        return userId;
    }

}
