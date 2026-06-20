package com.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.UserDto;
import com.jira.entities.AuthRequest;
import com.jira.entities.AuthResponse;
import com.jira.repository.UserRepository;
import com.jira.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	 UserService service;
	
	
	@PostMapping("/register")
	public UserDto registerUser(@RequestBody UserDto dto) {
		UserDto registerUser = service.registerUser(dto);
		
		return registerUser;
	}
	
	@PostMapping("/login")
	public AuthResponse login(@RequestBody AuthRequest authRequest) {
		AuthResponse loginUser = service.loginUser(authRequest);;
		
		
		return loginUser;
	}
	
}
