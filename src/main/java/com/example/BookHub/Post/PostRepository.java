package com.example.BookHub.Post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("postrepository")
public interface PostRepository {

	List<PostDTO> postList(int limit);

	PostDTO selectpost(long id);

	int totalPost();

	int insertMyPost(PostDTO postdto);

	int insertComment(CommentDTO commentdto);

	int updatePost(long postid);



}
