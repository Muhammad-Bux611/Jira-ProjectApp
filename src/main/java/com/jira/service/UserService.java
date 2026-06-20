package com.jira.service;

import java.util.List;

import com.jira.dto.UserDto;
import com.jira.entities.AuthRequest;
import com.jira.entities.AuthResponse;

public interface UserService {
	
	public UserDto registerUser(UserDto userDto);
	
	public AuthResponse loginUser(AuthRequest authRequest);
	
	public List<UserDto> getAllUser();
	
	public boolean deleteUserById(Integer userId);
	
	
	
}
