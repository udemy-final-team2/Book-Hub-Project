package com.example.BookHub.User;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserDTO> findUserByEmailAndSocialName(String email, String socialName) {
        return userRepository.findUserByEmailAndSocialName(email, socialName);
    }

    public UserDTO insertUserBySocial(UserDTO user) {
        if(findUserByEmailAndSocialName(user.getEmail(), user.getSocialName()).isEmpty())
            userRepository.insertUserBySocial(user);
        return user;
    }
    
    public int insertuser(UserDTO dto) {
		return userRepository.insertuser(dto);
	}

	public UserDTO loginuser(String email) {
		return userRepository.loginuser(email);
	}

	public UserDTO userinfo(int id) {
		return userRepository.userinfo(id);
	}

	public int updateuser(UserDTO dto) {
		return userRepository.updateuser(dto);
	}
	
	public int deleteuser(int id) {
		return userRepository.deleteuser(id);
	}

	public List<UserDTO> selectUserList(int limit) {
		return userRepository.selectUserList(limit);
	}

	public int totalUser() {
		return userRepository.totalUser();
	}

	public List<UserDTO> selectUserList() {
		return userRepository.selectUserList();
	}

	public List<UserDTO> selectUserList(String keyword) {
		return userRepository.selectUserList(keyword);
	}
	  
    
}
