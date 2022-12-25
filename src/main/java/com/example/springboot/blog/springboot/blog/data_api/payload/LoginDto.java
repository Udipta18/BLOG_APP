package com.example.springboot.blog.springboot.blog.data_api.payload;

import lombok.Data;

@Data
public class LoginDto {
  private String usernameOrEmail;
  private String password;
}
