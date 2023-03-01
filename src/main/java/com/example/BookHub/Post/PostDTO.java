package com.example.BookHub.Post;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Component("postdto")
public class PostDTO {
	private long id,userid;
	private String title,content,category;
	private Date createdAt;
	private String status;
	
	private String name; //username
	private String comment;//comment의 content

}
