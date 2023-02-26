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
		if (result == 1) {
			session.invalidate();
			log.info("회원삭제 완료");
		}
		return "home";
	}
	
	// 관리자페이지 이동
	@GetMapping("/usermanage")
	public ModelAndView userManage(@RequestParam(value="page", required=false, defaultValue="1")int page, UserDTO UserDTO, HttpSession session, @RequestParam(value="keyword", defaultValue="gmail",required=false) String keyword) {
		//admin정보
		Long id = ((com.example.BookHub.User.UserDTO)session.getAttribute(LOGIN_USER)).getId();
		log.info(LOGIN_USER);
		com.example.BookHub.User.UserDTO dto = userservice.userinfo(id);
		//유저리스트 조회 + 페이징
		int totalUser = userservice.totalUser();
		int limit = (page-1)*10;
		List<com.example.BookHub.User.UserDTO> userList = null;

		if(keyword == null) {
			userList = userservice.selectUserList(limit);
		}else {
			Map<String, Object> map = new HashMap();
			map.put("keyword", keyword);
			map.put("limit", 1);
			userList = userservice.selectUserList((Integer) map.get("limit"));
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("userList", userList);
		mv.addObject("totalUser",totalUser);
		mv.addObject("dto",dto);
		mv.setViewName("usermanage");
		log.info("유저정보조회");

		return mv;
	}
}
