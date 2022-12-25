package com.example.springboot.blog.springboot.blog.data_api.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.springboot.blog.springboot.blog.data_api.entity.Comments;
import com.example.springboot.blog.springboot.blog.data_api.entity.post;
import com.example.springboot.blog.springboot.blog.data_api.exception.BlogAPIException;
import com.example.springboot.blog.springboot.blog.data_api.exception.ResourceNotFoundException;
import com.example.springboot.blog.springboot.blog.data_api.payload.CommentDto;
import com.example.springboot.blog.springboot.blog.data_api.repository.CommentRepository;
import com.example.springboot.blog.springboot.blog.data_api.repository.PostRepository;
import com.example.springboot.blog.springboot.blog.data_api.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private ModelMapper mapper;
	
	private CommentRepository commentRepo;

	private PostRepository postRepo;

	public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo,ModelMapper mapper) {

		this.commentRepo = commentRepo;
		this.postRepo = postRepo;
		this.mapper=mapper;
	}

	@Override
	public CommentDto createComment(long id, CommentDto commentDto) {
		// TODO Auto-generated method stub

		Comments comments = mapToEntity(commentDto);

		post post1 = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post", "id", id));

		comments.setPost1(post1);

		Comments newComment = commentRepo.save(comments);

		return mapToDto(newComment);
	}

	private CommentDto mapToDto(Comments com) {
		CommentDto comm = mapper.map(com, CommentDto.class);

		/*
		 * comm.setId(com.getId()); comm.setNamed(com.getNamed());
		 * comm.setEmailIDD(com.getEmailIDD()); comm.setTextarea(com.getTextarea());
		 */
		return comm;

	}

	private Comments mapToEntity(CommentDto com) {
		Comments comm = mapper.map(com, Comments.class);
		/*
		 * comm.setId(com.getId()); comm.setNamed(com.getNamed());
		 * comm.setEmailIDD(com.getEmailIDD()); comm.setTextarea(com.getTextarea());
		 */
		return comm;

	}

	@Override
	public List<CommentDto> getCommentsByPostId(long postId) {
		// TODO Auto-generated method stub
		List<Comments> allComments = commentRepo.findByPost1Id(postId);

		return allComments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDto getConnectesById(long postId, long commentsId) {
		// TODO Auto-generated method stub
		post post1 = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

		Comments comment = commentRepo.findById(commentsId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

		if (!comment.getPost1().getId().equals(post1.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
		}

		return mapToDto(comment);
	}

	@Override
	public CommentDto updatedComment(long postId, long commentsId, CommentDto commentDto) {
		// TODO Auto-generated method stub

		post post1 = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

		Comments comment = commentRepo.findById(commentsId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

		if (!comment.getPost1().getId().equals(post1.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
		}
		
		comment.setNamed(commentDto.getNamed());
		comment.setEmailIDD(commentDto.getEmailIDD());
		comment.setTextarea(commentDto.getTextarea());
		
		Comments commentres=commentRepo.save(comment);
		

		return mapToDto(commentres);
	}

	@Override
	public void deleteComment(long postId, long commentsId) {
		// TODO Auto-generated method stub
		
		post post1 = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post", "id", postId));

		Comments comment = commentRepo.findById(commentsId)
				.orElseThrow(() -> new ResourceNotFoundException("Comments", "id", commentsId));

		if (!comment.getPost1().getId().equals(post1.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "comment does not belong to post");
		}
		
		commentRepo.delete(comment);
	}

}
