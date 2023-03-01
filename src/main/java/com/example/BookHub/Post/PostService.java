package com.example.BookHub.Post;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	
	public List<PostDTO> postList(Map<String, Object> map) {
		return postRepository.postList(map);
	}

	public PostDTO selectpost(long id) {
		return postRepository.selectpost(id);
	}

	public int totalPost() {
		return postRepository.totalPost();
	}

	public int totalPost(String keyword) {
		return postRepository.totalKeywordPost(keyword);
	}
	
	public int insertMyPost(PostDTO postdto) {
		return postRepository.insertMyPost(postdto);
	}

	public int insertComment(CommentDTO commentdto) {
		
		int result = postRepository.insertComment(commentdto);
		long postid = commentdto.getPostid();
		if(result == 1) {
			result = postRepository.updatePost(postid);
		}
		return result;
	}

	public List<PostDTO> userpostList(Map<String, Object> map) {
		return postRepository.userpostList(map);
	}
	public List<PostDTO> userpostkeywordList(Map<String, Object> map) {
		return postRepository.userpostkeywordList(map);
	}

	public List<PostDTO> postLimitList(int limit) {
		return postRepository.postLimitList(limit);
	}

	
}
