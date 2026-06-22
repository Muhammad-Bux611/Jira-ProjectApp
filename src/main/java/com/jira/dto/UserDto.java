package com.jira.dto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;
@Setter
@Getter

public class UserDto {


	private Integer userId;
	private String firstName;
	private String email;
	private String password;
	private LocalDateTime dateTime;
	
	private DepartmentDTO department;
	private RoleDto role;
	
}
