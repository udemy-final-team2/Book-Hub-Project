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
		UserDTO dto = userservice.loginUser(email);
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
		if(dto != null && dto.getRole().equals(Role.ADMIN)) {
			view = "redirect:/usermanage";
			log.info("관리자페이지이동");
		}else {
			view = "redirect:/folder/list";
		}
		return view;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		if(session.getAttribute(LOGIN_USER) != null){
			session.invalidate();	// 세션값 삭제
		}
		return "redirect:/home";
	}

	@PostMapping("/signupuser")
	public String insertUser(@ModelAttribute UserDTO dto) {
		//회원가입
		UserDTO insertedUser = userservice.insertUser(dto);
		return "signin";
	}

	@GetMapping("/mypage")
	public ModelAndView myPage(HttpSession session, UserDTO userDTO) {
		//마이페이지 내정보 불러오기
		Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
		UserDTO dto = userservice.userinfo(userId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("dto", dto);
		mv.setViewName("mypage");

		return mv;
	}

	@PostMapping("/user/updateuser")
	public String updateUser(@ModelAttribute UserDTO dto) {
		// 일반사용자 내정보 업데이트
		int result = userservice.updateUser(dto);
		if (result == 1) {
			log.info("내정보 변경완료");
		}
		return "redirect:/logout";
	}

	@GetMapping("/user/withdraw/{id}")
	public String deleteUser(HttpSession session, @PathVariable Long id) {
		// 일반사용자 회원탈퇴
		int result = userservice.deleteUser(id);
		return "redirect:/usermanage";
	}
	
	@GetMapping("/usermanage")
	public ModelAndView userManage(@RequestParam(value="page", required=false, defaultValue="1")int page, UserDTO UserDTO, HttpSession session, @RequestParam(value="keyword", required=false) String keyword) {
		// 관리자페이지 - 유저관리
		int totalUser = 0;
		int limit = (page-1)*10;
		List<UserDTO> userList = null;

		Long id = ((UserDTO)session.getAttribute(LOGIN_USER)).getId();
		UserDTO dto = userservice.userinfo(id);

		if(keyword == null) {
			totalUser = userservice.totalUser();
			userList = userservice.selectUserList(limit);
		}else {
			totalUser = userservice.totalUser(keyword);
			Map<String, Object> map = new HashMap<>();
			map.put("keyword", keyword);
			map.put("limit", limit);
			userList = userservice.selectUserList(map);
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.addObject("totalUser",totalUser);
		mv.addObject("dto",dto);
		mv.setViewName("usermanage");

		return mv;
	}
}
