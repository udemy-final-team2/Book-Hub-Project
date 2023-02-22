package com.example.BookHub.Post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.BookHub.User.UserDTO;
import com.example.BookHub.User.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller("postcontroller")
@RequiredArgsConstructor
@Slf4j
public class PostController {
	private final PostService postService;
	
	@GetMapping("/postlist")
	public ModelAndView postlist(@RequestParam(value="page", required=false, defaultValue="1")int page, @ModelAttribute UserDTO userdto) {
		// admin - user 
		// 문의관리 - 게시글리스트
//		int totalpost = postService.totalpost();
//		int limit = (page-1)*10;
		List<PostDTO> postList = postService.postList();
		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("totalpost",totalpost);
		mv.addObject("postList",postList);
		mv.setViewName("postlist");
		
		return mv;
		
	}
	
	@GetMapping("/userpostlist")
	public ModelAndView userpostlist(@RequestParam(value="page", required=false, defaultValue="1")int page, @ModelAttribute UserDTO userdto) {
		// admin - user 
		// 문의관리 - 게시글리스트
//		int totalpost = postService.totalpost();
//		int limit = (page-1)*10;
		List<PostDTO> postList = postService.postList();
		
		ModelAndView mv = new ModelAndView();
//		mv.addObject("totalpost",totalpost);
		mv.addObject("postList",postList);
		mv.setViewName("userpostlist");
		
		return mv;
		
	}
	
	
	@GetMapping("/post/{id}")
	public ModelAndView selectpost(@PathVariable("id")int id) {
		// admin - user 
		// 문의관리 - 상세글
		PostDTO postdto= postService.selectpost(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("postdto", postdto);
		mv.setViewName("post");
		
		return mv;
	}
	
	
	
	@GetMapping("/post/insert")
	public String insertPost() {
		
		return "insertpost";
	}
	
	
//	@GetMapping("/post/insert/{id}")
//	public String insertPost(@PathVariable("id")int id) {
//		
//		return "insertpost";
//	}
	
	
	
}
