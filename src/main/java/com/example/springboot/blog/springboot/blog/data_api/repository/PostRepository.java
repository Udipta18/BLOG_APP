package com.example.springboot.blog.springboot.blog.data_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.blog.springboot.blog.data_api.entity.post;

public interface PostRepository extends JpaRepository<post, Long> {

}
