package com.example.BookHub.User;


import java.util.List;
import java.util.Map;
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

	public UserDTO userinfo(Long id) {
		return userRepository.userinfo(id);
	}

	public int updateuser(UserDTO dto) {
		return userRepository.updateuser(dto);
	}
	
	public int deleteuser(Long id) {
		return userRepository.deleteuser(id);
	}

	public List<UserDTO> selectUserList(Map<String, Object> map) {
		return userRepository.selectUserkeywordList(map);
	}

	public int totalUser() {
		System.out.println(userRepository.totalUser());
		return userRepository.totalUser();
	}

	public List<UserDTO> selectUserList(int limit) {
		return userRepository.selectUserList(limit);
	}

    
}
