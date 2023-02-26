package com.example.BookHub.User;

import com.example.BookHub.Security.OAuth2.SocialType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
@Repository("userrepository")
public interface UserRepository extends UserDetailsService {
	Optional<UserDTO> findByEmail(String email);
	UserDTO loadUserByUsername(String userEmail);

    /*소셜 로그인 가입 여부 확인*/
	Optional<UserDTO> findBySocialTypeAndEmail(SocialType socialType, String email);

	UserDTO insertUser(UserDTO userDTO);

	/*마이페이지*/
	UserDTO userinfo(Long id);
	
	/*회원정보 수정*/
	int updateUser(UserDTO dto);
	
	/*회원정보 삭제*/
	int deleteUser(Long id);

	/*회원리스트 조회- 페이징, 검색어 - 수정중*/
	List<UserDTO> selectUserKeywordList(Map<String, Object> map);
	
	/*회원리스트 조회 -페이징*/
	List<UserDTO> selectUserList(int limit);
	
	/*회원수 조회 -페이징 */
	int totalUser();
}
