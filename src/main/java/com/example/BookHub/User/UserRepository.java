package com.example.BookHub.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository("userrepository")
public interface UserRepository {

    //로그인 & 회원가입
    Optional<UserDTO> findUserByEmailAndSocialname(String email, String socialname);

    void insertUserBySocial(UserDTO user);

}
