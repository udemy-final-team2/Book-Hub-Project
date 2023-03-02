package com.example.BookHub.User;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

	public UserDTO userinfo(Long id) {
		return userRepository.userinfo(id);
	}
	public Long insertUser(UserDTO userDTO) {return userRepository.insertUser(userDTO);}

	public UserDTO findBySocialId(String socialId) {return userRepository.findBySocialId(socialId);}

	public UserDTO loginUser(String email) {
		return userRepository.loginUser(email);
	}
	public int updateUser(UserDTO dto) {
		return userRepository.updateUser(dto);
	}
	public int deleteUser(Long id) {
		return userRepository.deleteUser(id);
	}
	public List<UserDTO> selectUserList(Map<String, Object> map) {
		return userRepository.selectUserKeywordList(map);
	}
	
	public List<UserDTO> selectUserList(int limit) {
		return userRepository.selectUserList(limit);
	}
	public int totalUser() {
		return userRepository.totalUser();
	}
	public int totalUser(String keyword) {
		return userRepository.keywordtotalUser(keyword);
	}
	public Long countByMale() {
		return userRepository.countByMale();
	}
	public Long countByFemale() {
		return userRepository.countByFemale();
	}
	public Long countByGoogle() {
		return userRepository.countByGoogle();
	}
	public Long countByNaver() {
		return userRepository.countByNaver();
	}
	public Long countByKakao() {
		return userRepository.countByKakao();
	}
}
