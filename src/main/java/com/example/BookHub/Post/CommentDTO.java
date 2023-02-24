package com.example.BookHub.Post;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Component("commentdto")
public class CommentDTO {
	private Long id,postid,userid;
	private String content;
	private Date created_at;
	
	
}
