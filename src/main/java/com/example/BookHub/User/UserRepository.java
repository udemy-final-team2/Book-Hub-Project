package com.example.BookHub.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository("userrepository")
public interface UserRepository {

    /*소셜 로그인 가입 여부 확인*/
    Optional<UserDTO> findUserByEmailAndSocialName(String email, String socialName);

    /*소셜 로그인 유저 데이터 저장*/
    void insertUserBySocial(UserDTO user);
    
    /*일반 로그인 회원가입*/
	int insertuser(UserDTO dto);
	
	/*일반 로그인 */
	UserDTO loginuser(String email);
	
	/*마이페이지*/
	UserDTO userinfo(int id);
	
	/*회원정보 수정*/
	int updateuser(UserDTO dto);
	
	/*회원정보 삭제*/
	int deleteuser(int id);

	/*회원리스트 조회 -페이징*/
	List<UserDTO> selectUserList(int limit);
	
	/*회원수 조회*/
	int totalUser();
	
	/*회원리스트 조회*/
	List<UserDTO> selectUserList();
	
	/*회원리스트 조회 - 검색*/
	List<UserDTO> selectUserList(String keyword);
	 
}
