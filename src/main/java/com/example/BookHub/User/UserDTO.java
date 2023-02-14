package com.example.BookHub.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@NoArgsConstructor
@Component("userdto")
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String role;
    private String socialname;

    @Builder
    public UserDTO(String name, String email, String role, String socialname) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.socialname = socialname;
    }

    public UserDTO update(String name){
        this.name = name;

        return this;
    }

    public void setId(int id){
        this.id = id;
    }

}
