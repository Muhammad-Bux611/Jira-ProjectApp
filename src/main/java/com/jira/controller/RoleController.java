package com.jira.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.RoleDto;
import com.jira.dto.UserDto;
import com.jira.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

	@Autowired
	RoleService roleService;
	@PostMapping("/createRoles")
	public RoleDto createRolesForProject(@RequestBody RoleDto roleDto) {
		
		RoleDto dto = roleService.createRoles(roleDto);
		
		return dto;
		
	}
	
	@PostMapping("/assignRole/role/{roleId}/users/{userId}")
	
	public UserDto assignRole(@PathVariable("roleId")Integer roleId,@PathVariable("userId") Integer userId) {
		
		UserDto userDto = roleService.assignRoleToUser(roleId, userId);
		
		return userDto;
	}
	
}
