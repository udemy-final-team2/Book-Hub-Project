package com.example.BookHub.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userservice")
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<UserDTO> findUserByEmailAndSocialname(String email, String socialname) {
        return userRepository.findUserByEmailAndSocialname(email, socialname);
    }

    public UserDTO insertUserBySocial(UserDTO user) {
        if(findUserByEmailAndSocialname(user.getEmail(), user.getSocialname()).isEmpty())
            userRepository.insertUserBySocial(user);
        return user;
    }
}
