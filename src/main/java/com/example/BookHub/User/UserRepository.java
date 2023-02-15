package com.example.BookHub.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Mapper
@Repository("userrepository")
public interface UserRepository {

    /*소셜 로그인 가입 여부 확인*/
    Optional<UserDTO> findUserByEmailAndSocialName(String email, String socialName);

    /*소셜 로그인 유저 데이터 저장*/
    void insertUserBySocial(UserDTO user);
}
