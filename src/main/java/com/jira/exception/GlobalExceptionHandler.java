package com.jira.exception;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jira.payloads.CustomExceptionResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomExceptionResponse> resource(ResourceNotFoundException ex) {
		
		CustomExceptionResponse response = new CustomExceptionResponse();
		response.setMessage(ex.getMessage());
		
		response.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<CustomExceptionResponse>(response,HttpStatus.NOT_FOUND);
	}

}
