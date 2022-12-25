package com.example.springboot.blog.springboot.blog.data_api.service;



import com.example.springboot.blog.springboot.blog.data_api.payload.PostDto;
import com.example.springboot.blog.springboot.blog.data_api.payload.PostResponse;

public interface PostService {

	
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPost(int pageNo,int pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(long id);
	
	PostDto updatePost(PostDto postDto,long id);
	
	void deleteById(long id);
}
