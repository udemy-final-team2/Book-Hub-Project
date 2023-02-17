package com.example.BookHub.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component("userdto")
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String socialName;
    private String status;

    @Builder
    public UserDTO(String name, String email, String role, String socialName) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.socialName = socialName;
    }
    public UserDTO update(String name){
        this.name = name;
        return this;
    }
}
