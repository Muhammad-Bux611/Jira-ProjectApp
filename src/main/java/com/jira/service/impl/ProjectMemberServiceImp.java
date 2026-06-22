package com.jira.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jira.dto.ProjectDTO;
import com.jira.dto.ProjectMemberDTO;
import com.jira.dto.UserDto;
import com.jira.service.ProjectMemberService;
@Service
public class ProjectMemberServiceImp implements ProjectMemberService {

	@Override
	public ProjectMemberDTO createProjectMember(ProjectMemberDTO projectMemberDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectMemberDTO getProjectMemberById(Integer projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectMemberDTO> getAllProjectMembers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProjectMember(Integer projectMemberId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ProjectMemberDTO> listOfProjectMemberByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectMemberDTO> listOfProjectMemberByProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> listOfUserUsingProjectId(Integer projectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjectDTO> listOfPrpjectUsingUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
