package com.example.BookHub.User;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

	public UserDTO userinfo(Long id) {
		return userRepository.userinfo(id);
	}
	public UserDTO insertUser(UserDTO userDTO) {return userRepository.insertUser(userDTO);}

	public int updateUser(UserDTO dto) {
		return userRepository.updateUser(dto);
	}
	
	public int deleteUser(Long id) {
		return userRepository.deleteUser(id);
	}

	public int totalUser() {
		System.out.println(userRepository.totalUser());
		return userRepository.totalUser();
	}

	public List<UserDTO> selectUserList(int limit) {
		return userRepository.selectUserList(limit);
	}
    
}
