package com.example.springboot.blog.springboot.blog.data_api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PsswordEncoderGenerator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
   PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
   System.out.println(passwordEncoder.encode("password"));
	}

}
