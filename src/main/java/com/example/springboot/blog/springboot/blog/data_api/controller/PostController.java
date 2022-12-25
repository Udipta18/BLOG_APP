package com.example.springboot.blog.springboot.blog.data_api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.blog.springboot.blog.data_api.payload.PostDto;
import com.example.springboot.blog.springboot.blog.data_api.payload.PostResponse;
import com.example.springboot.blog.springboot.blog.data_api.service.PostService;
import com.example.springboot.blog.springboot.blog.data_api.utils.AppConstants;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	
	private PostService postService;

	public PostController(PostService postService) {
		
		this.postService = postService;
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping()
	public ResponseEntity<PostDto> createPost( @Valid @RequestBody PostDto postDto){
		
		return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
		
	}

	@GetMapping()
	public PostResponse getAllPosts(
			@RequestParam(value="pageNo", defaultValue =AppConstants.DEFAULT_PAGE_NO, required = false ) int pageNo,
			@RequestParam(value="pageSize", defaultValue =AppConstants.DEFAULT_PAGE_SIZE, required = false ) int pageSize,
			@RequestParam(value="sortBy", defaultValue =AppConstants.DEFAULT_SORT_BY, required = false ) String sortBy,
			@RequestParam(value="sortDir", defaultValue =AppConstants.DEFAULT_SORT_DIRECTION, required = false ) String sortDir
			
			
			){
		return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
	}
	    
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
		return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatedPost(@Valid @RequestBody PostDto postDto,@PathVariable(name="id") long id){
		PostDto postResponse=postService.updatePost(postDto, id);
		
		return ResponseEntity.ok(postResponse);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id){
		postService.deleteById(id);
		
	
		return ResponseEntity.ok("post deleted successfully");
	}
	
}
