package com.example.BookHub.Post;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
	private final PostRepository postRepository;

	public List<PostDTO> postList() {
		return postRepository.postList();
	}

	public PostDTO selectpost(int id) {
		return postRepository.selectpost(id);
	}
	
	
}
