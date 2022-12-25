package com.example.springboot.blog.springboot.blog.data_api.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.springboot.blog.springboot.blog.data_api.payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogApiException(BlogAPIException resourceNotFoundException,WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception resourceNotFoundException,WebRequest webRequest){
		ErrorDetails errorDetails=new ErrorDetails(new Date(), resourceNotFoundException.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	
	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class) public
	 * ResponseEntity<Object>
	 * handleMethodArgumentNotValidApiException(MethodArgumentNotValidException
	 * exception,WebRequest webRequest){
	 * 
	 * Map<String,String> errors=new HashMap<>();
	 * exception.getBindingResult().getAllErrors().forEach((error) ->{ String
	 * fieldName= ((FieldError)error).getField(); String
	 * meesage=error.getDefaultMessage(); errors.put(fieldName, meesage); });
	 * 
	 * return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST); }
	 */
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		Map<String,String> errors=new HashMap<>();
		 ex.getBindingResult().getAllErrors().forEach((error) ->{ String
		  fieldName= ((FieldError)error).getField(); String
		  meesage=error.getDefaultMessage(); errors.put(fieldName, meesage); });
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
}
