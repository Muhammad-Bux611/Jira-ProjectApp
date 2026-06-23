package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.ProjectDTO;
import com.jira.dto.ProjectMemberDTO;
import com.jira.dto.UserDto;
import com.jira.entities.Project;
import com.jira.entities.ProjectMember;
import com.jira.entities.Users;
import com.jira.exception.ResourceNotFoundException;
import com.jira.repository.ProjectMemberRepository;
import com.jira.repository.ProjectRepository;
import com.jira.repository.UserRepository;
import com.jira.service.ProjectMemberService;
@Service
public class ProjectMemberServiceImp implements ProjectMemberService {
	
	@Autowired
	ProjectMemberRepository projectMemberRepository;
	@Autowired
	ModelMapper mapper;

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public ProjectMemberDTO createProjectMember(Integer projectId,Integer userId) {
		
		Users user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user with"+
		userId+" is not present or found"
				));
		

		Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("project with"+
		projectId+" is not present or found"
				));
//		
//		
		ProjectMember projectMember = new ProjectMember();
		projectMember.setProject(project);
		projectMember.setUsers(user);
		
		ProjectMember savedProjectMember = projectMemberRepository.save(projectMember);
		
		return mapper.map(savedProjectMember, ProjectMemberDTO.class);
	}

	@Override
	public ProjectMemberDTO getProjectMemberById(Integer projectMemberId) {
		
		ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow(()->new ResourceNotFoundException("ProjectMember with id"+projectMemberId+" is not found"));
		
		return mapper.map(projectMember, ProjectMemberDTO.class);
	}

	@Override
	public List<ProjectMemberDTO> getAllProjectMembers() {
		// TODO Auto-generated method stub
		
		List<ProjectMember> listOfProjectMembers = projectMemberRepository.findAll();
		List<ProjectMemberDTO> listOfProjectMemberDTO = listOfProjectMembers.stream().map(projectmember->mapper.map(projectmember, ProjectMemberDTO.class)).toList();
		
		return listOfProjectMemberDTO;
	}

	@Override
	public boolean deleteProjectMember(Integer projectMemberId) {
		// TODO Auto-generated method stub
		boolean flag =false;
		ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow(()->new ResourceNotFoundException("ProjectMember with id"+projectMemberId+" is not found"));
		
		if (projectMember!=null) {
			projectMemberRepository.delete(projectMember);
			flag = true;
		}
		
		
		return flag;
	}

	@Override
	public List<ProjectMemberDTO> listOfProjectMemberByUserId(Integer userId) {
		
//		List<ProjectMember> projectMembers = projectMemberRepository.findByUsersUserId(userId).orElseThrow(()->new ResourceNotFoundException("Project Members are not present with the user id"+userId));
//		
//		
		return null;
	}

	@Override
	public List<ProjectMemberDTO> listOfProjectMemberByProjectId(Integer projectId) {
		
		
		List<ProjectMember> list = projectMemberRepository.findAllByProjectProjectId(projectId);
		
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Project is not assgined yet");
		
		}
		
		List<ProjectMemberDTO> projectMemberDtos = list.stream().map(projectMember->mapper.map(projectMember, ProjectMemberDTO.class)).toList();
		
		
		return projectMemberDtos;
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

	@Override
	public boolean checkdublicateAssignment(Integer projectId, Integer userId) {
		// TODO Auto-generated method stub
		
		boolean existsByProjectProjectIdAndUserUserId = projectMemberRepository.existsByProjectProjectIdAndUsersUserId(projectId, userId);
		
		if (existsByProjectProjectIdAndUserUserId) {
			return true;
		}
		
		return false;
	}

	@Override
	public ProjectMemberDTO getUserForSpecificProject(Integer projectId, Integer userId) {
		// TODO Auto-generated method stub
		
		ProjectMember byProjectProjectIdAndUserUserId = projectMemberRepository.findByProjectProjectIdAndUsersUserId(projectId, userId);
		
		return mapper.map(byProjectProjectIdAndUserUserId, ProjectMemberDTO.class);
	}
	@Override
	public boolean removeUserFromProject(Integer projectId, Integer userId) {
		
		boolean isRemoved= false;
		
		boolean existsByProjectProjectIdAndUsersUserId = projectMemberRepository.existsByProjectProjectIdAndUsersUserId(projectId, userId);
		
		if (existsByProjectProjectIdAndUsersUserId) {
			
			ProjectMember byProjectProjectIdAndUserUserId = projectMemberRepository.findByProjectProjectIdAndUsersUserId(projectId, userId);
			
			projectMemberRepository.delete(byProjectProjectIdAndUserUserId);
			
			isRemoved=true;
			
		}
		
		return isRemoved;
		
	}

	@Override
	public List<ProjectDTO> getAllProjectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		
		List<ProjectMember> listOfProject = projectMemberRepository.findAllByUsersUserId(userId);
		
		if (listOfProject.isEmpty()) {
			throw new ResourceNotFoundException("Not any Project that is assigned to specific user that has id "+userId);
		}
		
		List<ProjectDTO> list = listOfProject.stream().map(project->mapper.map(project.getProject(), ProjectDTO.class)).toList();
		return list;
	}

}
