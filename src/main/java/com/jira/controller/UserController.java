package com.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.UserDto;
import com.jira.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public List<UserDto> getAllUser() {
	return	userService.getAllUser();
	}

	
	@DeleteMapping("/{userId}")
	
	public String deleteUser(@PathVariable("userId") Integer userId) {
		
		boolean deleteUserById = userService.deleteUserById(userId);
		if (deleteUserById) {
			return "user is deleted successfully";
		}else {
			return "this user is not present in the database";
		}
		
	}
}
