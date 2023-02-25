package com.example.BookHub.Post;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;
	
	public List<PostDTO> postList(int limit) {
		return postRepository.postList(limit);
	}

	public PostDTO selectpost(long id) {
		return postRepository.selectpost(id);
	}

	public int totalPost() {
		return postRepository.totalPost();
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
	
}
