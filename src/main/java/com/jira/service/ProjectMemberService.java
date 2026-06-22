package com.jira.service;

import java.util.List;

import com.jira.dto.ProjectDTO;
import com.jira.dto.ProjectMemberDTO;
import com.jira.dto.UserDto;

public interface ProjectMemberService {
	
	public ProjectMemberDTO createProjectMember(ProjectMemberDTO projectMemberDTO);
	public ProjectMemberDTO getProjectMemberById(Integer projectId);
	public List<ProjectMemberDTO> getAllProjectMembers();
	public boolean deleteProjectMember(Integer projectMemberId);
	public List<ProjectMemberDTO> listOfProjectMemberByUserId(Integer userId);
	public List<ProjectMemberDTO> listOfProjectMemberByProjectId(Integer projectId);
	public List<UserDto> listOfUserUsingProjectId(Integer projectId);
	public List<ProjectDTO> listOfPrpjectUsingUserId(Integer userId);
 
}
