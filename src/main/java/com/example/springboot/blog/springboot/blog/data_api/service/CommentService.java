package com.example.springboot.blog.springboot.blog.data_api.service;

import java.util.List;

import com.example.springboot.blog.springboot.blog.data_api.payload.CommentDto;

public interface CommentService {

	
	 CommentDto createComment(long id,CommentDto commentDto);
	 
	 List<CommentDto>  getCommentsByPostId(long postId);
	 
	 CommentDto getConnectesById(long postId,long commentsId);
	 
	 CommentDto updatedComment(long postId,long commentsId,CommentDto commentDto);
	 
	 void deleteComment(long postId,long commentsId);
}
