package com.example.BookHub.User;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class UserController {

	private final UserService userservice;

	@PostMapping("/login")
	public String login(String email, String password, HttpSession session) {
		// 일반로그인
		UserDTO dto = userservice.loginuser(email);
		String view = "";

		if (dto == null) {
			log.info("가입되지않음");
			view = "signin";
		}

		if (dto != null && email.equals(dto.getEmail())) {
			if (password.equals(dto.getPassword())) {
				session.setAttribute(LOGIN_USER, dto);
			}
		}

		if(dto != null && dto.getRole().equals("관리자")) {
			view = "redirect:/usermanage";
			log.info("관리자페이지이동");
		}else {
			view = "home";
		}
		return view;
	}


	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 희진_ 로그아웃 테스트용 jsp에도 버튼 추가
		if(session.getAttribute(LOGIN_USER) != null){
			session.invalidate();	// 세션값 삭제
		}
		return "home";
	}

	@PostMapping("/signupuser")
	public String insertuser(@ModelAttribute UserDTO dto) {
		//일반사용자 가입
		int result = userservice.insertuser(dto);
		return "signin";
	}


	@GetMapping("/mypage")
	public ModelAndView mypage(HttpSession session, UserDTO userdto) {
		//마이페이지 내정보 불러오기
		Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
		UserDTO dto = userservice.userinfo(userId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto", dto);
		mv.setViewName("mypage");

		return mv;
	}

	@PostMapping("/user/updateuser")
	public String updateuser(@ModelAttribute UserDTO dto) {
		// 일반사용자 내정보 업데이트
		int result = userservice.updateuser(dto);
		if (result == 1) {
			log.info("내정보 변경완료");
		}
		return "redirect:/logout";
	}

	@GetMapping("/user/withdraw/{id}")
	public String deleteuser(HttpSession session, @PathVariable Long id) {
		// 일반사용자 회원탈퇴
		int result = userservice.deleteuser(id);
		if (result == 1) {
			session.invalidate();
			log.info("회원삭제 완료");
		}
		return "home";
	}
	
	// 관리자페이지 이동
    @GetMapping("/usermanage")
    public ModelAndView usermanage(@RequestParam(value="page", required=false, defaultValue="1")int page, UserDTO UserDTO, HttpSession session, @RequestParam(value="keyword", defaultValue="gmail",required=false) String keyword) {
    		//admin정보 
    		Long id = ((UserDTO)session.getAttribute(LOGIN_USER)).getId();
    		log.info(LOGIN_USER);
    		UserDTO dto = userservice.userinfo(id);
    		//유저리스트 조회 + 페이징
    		int totalUser = userservice.totalUser();
    		int limit = (page-1)*10;
    		List<UserDTO> userList = null;
    		
    		if(keyword == null) {
    			userList = userservice.selectUserList(limit);
    		}else {
    			Map<String, Object> map = new HashMap();
    			map.put("keyword", keyword);
    			map.put("limit", 1);
    			userList = userservice.selectUserList(map);
    		}
    		
    		ModelAndView mv = new ModelAndView();
    		mv.addObject("userList",userList);
    		mv.addObject("totalUser",totalUser);
    		mv.addObject("dto",dto);
    		mv.setViewName("usermanage");
    		log.info("유저정보조회");
    		
        return mv;
    }
}
