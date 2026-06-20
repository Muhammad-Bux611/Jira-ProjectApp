package com.jira.service;

import java.util.List;

import com.jira.dto.RoleDto;
import com.jira.dto.UserDto;

public interface RoleService {
	
	public RoleDto createRoles(RoleDto roleDto);
	public List<RoleDto> getAllRoles();
	public UserDto assignRoleToUser(Integer roleId, Integer userId);

}
