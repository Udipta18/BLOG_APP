package com.example.springboot.blog.springboot.blog.data_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.blog.springboot.blog.data_api.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Long>{

	
	List<Comments> findByPost1Id(long postId);
}
