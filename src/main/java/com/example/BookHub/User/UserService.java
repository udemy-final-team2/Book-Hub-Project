package com.example.BookHub.User;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
