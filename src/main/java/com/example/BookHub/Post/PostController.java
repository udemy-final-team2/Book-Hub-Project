package com.example.BookHub.Post;

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

import com.example.BookHub.User.UserDTO;
import lombok.RequiredArgsConstructor;

@Controller("postcontroller")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;

	@GetMapping("/postlist")
	public ModelAndView postlist(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			HttpSession session, @RequestParam(value = "keyword", required = false) String keyword) {
		// 문의 - 게시글리스트
		long id = ((UserDTO) session.getAttribute(LOGIN_USER)).getId();
		String name = ((UserDTO) session.getAttribute(LOGIN_USER)).getName();
		String role = ((UserDTO) session.getAttribute(LOGIN_USER)).getRole().toString();
		List<PostDTO> postList = null;

		int totalPost = 0;
		int limit = (page - 1) * 10;
		Map<String, Object> map = new HashMap<>();

		if (keyword != null) {
			//검색어가 있는경우
			totalPost = postService.totalPost(keyword);
			if (((UserDTO) session.getAttribute(LOGIN_USER)).getRole().toString().equals("ADMIN")) {
				map.put("keyword", keyword);
				map.put("limit", limit);
				postList = postService.postList(map);
			} else {
				map.put("id", id);
				map.put("limit", limit);
				map.put("keyword", keyword);
				postList = postService.userpostkeywordList(map);
			}
		} else {
			//검색어가 없는경우
			totalPost = postService.totalPost();
			if(((UserDTO) session.getAttribute(LOGIN_USER)).getRole().toString().equals("ADMIN")) {
				//관리자
				postList = postService.postLimitList(limit);
			}else {
				//유저
				map.put("id", id);
				map.put("limit", limit);
				postList = postService.userpostList(map);
			}
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("totalPost", totalPost);
		mv.addObject("postList", postList);
		mv.addObject("role", role);
		mv.addObject("name", name);
		mv.setViewName("postlist");

		return mv;
	}
	
	@GetMapping("/post/{id}")
	public ModelAndView selectpost(HttpSession session, @PathVariable Long id) {
		// 문의 - 상세글
		String role = ((UserDTO) session.getAttribute(LOGIN_USER)).getRole().toString();
		PostDTO postdto= postService.selectpost(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("postdto", postdto);
		mv.addObject("role", role);
		mv.setViewName("post");
		
		return mv;
	}
	
	@GetMapping("/post/insert")
	public String insertPost() {
		//문의글작성 페이지이동
		return "insertpost";
	}
	
	@PostMapping("/post/insert/{id}")
	public String insertMyPost(HttpSession session, PostDTO postdto,@PathVariable Long id ){
		//문의글 작성
		postService.insertMyPost(postdto);
		return "redirect:/postlist";
	}
	
	@PostMapping("/post/comment")
	public String insertComment(@ModelAttribute CommentDTO commentdto){
		//코멘트 작성
		postService.insertComment(commentdto);
		return "redirect:/postlist";
	}
	
	@GetMapping("/post/qna")
	public String postqna() {
		//문의글작성 페이지이동
		return "postqna";
	}
	
}
