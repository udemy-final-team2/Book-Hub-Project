package com.example.BookHub.Post;

import static com.example.BookHub.Util.SessionConst.LOGIN_USER;
import java.util.List;
import javax.servlet.http.HttpSession;

import com.example.BookHub.User.UserDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller("postcontroller")
@RequiredArgsConstructor
public class PostController {
	private final PostService postService;
	
	@GetMapping("/postlist")
	public ModelAndView postlist(@RequestParam(value="page", required=false, defaultValue="1")int page,HttpSession session) {
		// 문의 - 게시글리스트
		String name = ((UserDTO) session.getAttribute(LOGIN_USER)).getName();

		int totalPost = postService.totalPost();
		int limit = (page-1)*10;
		
		List<PostDTO> postList = postService.postList(limit);
		ModelAndView mv = new ModelAndView();
		mv.addObject("totalPost",totalPost);
		mv.addObject("postList",postList);
		mv.addObject("name",name);
		mv.setViewName("postlist");
		
		return mv;
		
	}
	
	@GetMapping("/post/{id}")
	public ModelAndView selectpost(HttpSession session, @PathVariable Long id) {
		// 문의 - 상세글
		String role = String.valueOf(((UserDTO) session.getAttribute(LOGIN_USER)).getRole());
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
	
}
