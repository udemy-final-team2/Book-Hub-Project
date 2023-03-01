package com.example.BookHub.Post;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("postrepository")
public interface PostRepository {

	List<PostDTO> postList(Map<String, Object> map);

	List<PostDTO> postLimitList(int limit);
	
	List<PostDTO> userpostList(Map<String, Object> map);
	
	List<PostDTO> userpostkeywordList(Map<String, Object> map);

	PostDTO selectpost(long id);

	int totalPost();
	
	int totalKeywordPost(String keyword);

	int insertMyPost(PostDTO postdto);

	int insertComment(CommentDTO commentdto);

	int updatePost(long postid);

	

	
	


}
