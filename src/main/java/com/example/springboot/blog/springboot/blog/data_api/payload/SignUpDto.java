package com.example.springboot.blog.springboot.blog.data_api.payload;

import lombok.Data;

@Data
public class SignUpDto {
  
	private String name;
	private String username;
	private String email;
	private String password;
	
}
