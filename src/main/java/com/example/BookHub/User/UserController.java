package com.example.BookHub.User;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.example.BookHub.Security.OAuth2.CustomOAuth2UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserService userservice;

    @PostMapping("/loginuser")
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

		if (dto != null && email.equals(dto.getEmail())) {
			if (password.equals(dto.getPassword())) {
				session.setAttribute(LOGIN_USER, dto);
			}
		}
		if(dto != null && dto.getRole().toString().equals("ADMIN")) {
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
		Long insertedUser = userservice.insertUser(dto);
		return "signin";
	}
    @GetMapping("/mypage")
    public ModelAndView myPage(HttpSession session, Authentication authentication) {
        UserDTO dto;
        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            String socialId = customOAuth2UserService.getUserId(authentication);
            dto = userservice.findBySocialId(socialId);
        } else {
            Long userId = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
            dto = userservice.userinfo(userId);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("dto", dto);
        mv.setViewName("mypage");

        return mv;
    }

    @PostMapping("/user/updateuser")
    public String updateUser(@ModelAttribute UserDTO dto) {
        // 일반사용자 내정보 업데이트
        int result = userservice.updateUser(dto);
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

    @GetMapping("/chartlist")
    public Map<String, Long> getChart() {
        Map<String, Long> map = new HashMap<>();
        Long countByMale = userservice.countByMale();
        Long countByFemale = userservice.countByFemale();
        Long countByGoogle = userservice.countByGoogle();
        Long countByKakao = userservice.countByKakao();
        Long countByNaver = userservice.countByNaver();
        map.put("countByMale", countByMale);
        map.put("countByFemale",countByFemale);
        map.put("countByGoogle",countByGoogle);
        map.put("countByKakao",countByKakao);
        map.put("countByNaver",countByNaver);
        return map;
    }
    @GetMapping("/chart")
    public String chart() {
        return "chart";
    }
}

