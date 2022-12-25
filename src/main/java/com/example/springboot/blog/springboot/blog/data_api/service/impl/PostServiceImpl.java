package com.example.springboot.blog.springboot.blog.data_api.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springboot.blog.springboot.blog.data_api.entity.post;
import com.example.springboot.blog.springboot.blog.data_api.exception.ResourceNotFoundException;
import com.example.springboot.blog.springboot.blog.data_api.payload.PostDto;
import com.example.springboot.blog.springboot.blog.data_api.payload.PostResponse;
import com.example.springboot.blog.springboot.blog.data_api.repository.PostRepository;
import com.example.springboot.blog.springboot.blog.data_api.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private ModelMapper mapper;

	private PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository, ModelMapper mapper) {

		this.postRepository = postRepository;
		this.mapper = mapper;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		// TODO Auto-generated method stub

		post postreq = mapToEntity(postDto);
		/*
		 * postreq.setTitle(postDto.getTitle());
		 * postreq.setDescription(postDto.getDescription());
		 * postreq.setContent(postDto.getContent());
		 */

		post newPost = postRepository.save(postreq);

		/*
		 * PostDto postRes=new PostDto(); postRes.setId(newPost.getId());
		 * postRes.setTitle(newPost.getTitle());
		 * postRes.setDescription(newPost.getDescription());
		 * postRes.setContent(newPost.getContent());
		 */
		PostDto postRes = mapToDto(newPost);

		return postRes;

	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		// TODO Auto-generated method stub

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		Page<post> posts = postRepository.findAll(pageable);

		List<post> listOfPosts = posts.getContent();

		List<PostDto> postResponse = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

		PostResponse postResponse2 = new PostResponse();
		postResponse2.setContent(postResponse);
		postResponse2.setPageNo(pageNo);
		postResponse2.setPageSize(pageSize);
		postResponse2.setTotalElements(posts.getTotalElements());
		postResponse2.setTotalPages(posts.getTotalPages());
		postResponse2.setLast(posts.isLast());

		return postResponse2;

	}

	private PostDto mapToDto(post newPost) {

		PostDto postDto = mapper.map(newPost, PostDto.class);
		/*
		 * PostDto postRes = new PostDto(); postRes.setId(newPost.getId());
		 * postRes.setTitle(newPost.getTitle());
		 * postRes.setDescription(newPost.getDescription());
		 * postRes.setContent(newPost.getContent());
		 */
		return postDto;
	}

	private post mapToEntity(PostDto postDto) {

		post posten = mapper.map(postDto, post.class);
		/*
		 * post postreq = new post(); postreq.setTitle(postDto.getTitle());
		 * postreq.setDescription(postDto.getDescription());
		 * postreq.setContent(postDto.getContent());
		 */
		return posten;
	}

	@Override
	public PostDto getPostById(long id) {
		// TODO Auto-generated method stub
		post postres = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		return mapToDto(postres);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// TODO Auto-generated method stub
		post postres = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));
		postres.setTitle(postDto.getTitle());
		postres.setDescription(postDto.getDescription());
		postres.setContent(postDto.getContent());

		post newPost = postRepository.save(postres);

		return mapToDto(newPost);
	}

	@Override
	public void deleteById(long id) {
		// TODO Auto-generated method stub

		post postres = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

		postRepository.delete(postres);

	}

}
