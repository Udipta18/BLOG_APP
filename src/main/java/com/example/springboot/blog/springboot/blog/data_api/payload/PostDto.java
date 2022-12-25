package com.example.springboot.blog.springboot.blog.data_api.payload;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDto {
  
	 private Long Id;
	 
	 @NotEmpty
	 @Size(min=2,message="post title should have atleast 2 character")
	 private String title;
	 
	 @NotEmpty
	 @Size(min=10,message="post description should have atleast 10 character")
	 private String description;
	 @NotEmpty
	 private String content;
	 private Set<CommentDto> comments;
	 
}
