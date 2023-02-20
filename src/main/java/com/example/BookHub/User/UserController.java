package com.example.BookHub.User;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
				session.setAttribute("loginid", dto.getId());
			}
		}
		
		if(dto.getRole().equals("관리자")) {
			view = "usermanage";
			log.info("관리자페이지이동");
		}else {
			view = "home";
		}
		return view;
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// 희진_ 로그아웃 테스트용 jsp에도 버튼 추가
		// 추후 세션값 삭제 
		if(session.getAttribute("loginid") != null){
				session.removeAttribute("loginid");
			}
	
		return "home";

	}
	
	@PostMapping("/signupuser")
	public String insertuser(@ModelAttribute UserDTO dto) {
		//일반사용자 가입
		int result = userservice.insertuser(dto);
		return "signin";
	}
	
	
	@GetMapping("/user/{id}")
	public ModelAndView mypage(HttpSession session, UserDTO userdto,@PathVariable("id")int id) {
		//마이페이지 내정보 불러오기
		//int id = (int)session.getAttribute("loginid");
		UserDTO dto = userservice.userinfo(id);
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
		return "home";
	}
	
	@GetMapping("/user/withdraw/{id}")
	public String deleteuser(HttpSession session,@PathVariable int id) {
		// 일반사용자 회원탈퇴  
		int result = userservice.deleteuser(id);
		if (result == 1) {
			session.removeAttribute("loginid");
			log.info("회원삭제 완료");
		}
		
		return "home";
	}

	
	
}
