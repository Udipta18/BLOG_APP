package com.example.springboot.blog.springboot.blog.data_api.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
   
	
	    private long id;
	    private String named;
	    private String emailIDD;
	    private String  textarea;
		
}
