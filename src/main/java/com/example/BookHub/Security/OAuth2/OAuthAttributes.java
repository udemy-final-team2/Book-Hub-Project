package com.example.BookHub.Security.OAuth2;

import com.example.BookHub.Security.OAuth2.UserInfo.GoogleOAuth2UserInfo;
import com.example.BookHub.Security.OAuth2.UserInfo.KaKaoOAuth2UserInfo;
import com.example.BookHub.Security.OAuth2.UserInfo.NaverOAuth2UserInfo;
import com.example.BookHub.Security.OAuth2.UserInfo.OAuth2UserInfo;
import com.example.BookHub.User.Role;
import com.example.BookHub.User.UserDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OAuthAttributes {

    private String nameAttributeKey;
    private OAuth2UserInfo oauth2UserInfo;

    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {
        if (socialType == SocialType.NAVER) {
            return ofNaver(userNameAttributeName, attributes);
        }
        if (socialType == SocialType.KAKAO) {
            return ofKakao(userNameAttributeName, attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
         .nameAttributeKey(userNameAttributeName)
         .oauth2UserInfo(new KaKaoOAuth2UserInfo(attributes))
         .build();
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
         .nameAttributeKey(userNameAttributeName)
         .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
         .build();
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
         .nameAttributeKey(userNameAttributeName)
         .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
         .build();
    }

    public UserDTO toEntity(SocialType socialType, OAuth2UserInfo oAuth2UserInfo) {
        return UserDTO.builder()
         .name(oAuth2UserInfo.getName())
         .email(oAuth2UserInfo.getEmail())
         .role(Role.USER)
         .socialType(socialType)
         .socialId(oAuth2UserInfo.getId())
         .build();
    }
}