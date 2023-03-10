package com.example.BookHub.Security.OAuth2;

import com.example.BookHub.User.Role;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class CustomOAuth2User extends DefaultOAuth2User {
    private Long id;
    private String email;
    private String name;
    private Role role;

    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities,
                            Map<String, Object> attributes, String nameAttributeKey,
                            Long id, String email, String name, Role role) {
        super(authorities, attributes, nameAttributeKey);
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }

}
