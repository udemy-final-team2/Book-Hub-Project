package com.example.BookHub.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("userdto")
public class UserDTO {

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String socialName;
    
    private String keyword;

    
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
    
    //일반로그인
	public UserDTO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ",  role=" + role
				+ ", socialName=" + socialName + "]";
	}

	
    
    
}
