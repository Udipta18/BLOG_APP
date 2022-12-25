package com.example.springboot.blog.springboot.blog.data_api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.blog.springboot.blog.data_api.payload.CommentDto;
import com.example.springboot.blog.springboot.blog.data_api.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		
		this.commentService = commentService;
	}
	
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value="postId")long id,@RequestBody CommentDto commentDto){
		
		return new ResponseEntity<>(commentService.createComment(id, commentDto),HttpStatus.CREATED);
	}
	
	
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentByPOstId( @PathVariable(value="postId") long postId){
		return commentService.getCommentsByPostId(postId);
	}
	
	@GetMapping("/posts/{postId}/comments/{commentsId}")
	public ResponseEntity<CommentDto> getCommnetsById(@PathVariable(value="postId")  long postId, @PathVariable(value="commentsId") long commentsId){
		
		CommentDto commrespone=commentService.getConnectesById(postId, commentsId);
		
		return new ResponseEntity<>(commrespone,HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}/comments/{commentsId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId")  long postId, @PathVariable(value="commentsId") long commentsId,@RequestBody CommentDto commentDto){
		
		CommentDto commres=commentService.updatedComment(postId, commentsId, commentDto);
		
		return new ResponseEntity<>(commres,HttpStatus.OK);
	}
	
	@DeleteMapping("/posts/{postId}/comments/{commentsId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value="postId")  long postId, @PathVariable(value="commentsId") long commentsId){
		
		commentService.deleteComment(postId, commentsId);
		
		return ResponseEntity.ok("comment deleted successfully");
	}
	
}
