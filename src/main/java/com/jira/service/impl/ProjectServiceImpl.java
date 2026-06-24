package com.jira.service.impl;

import java.util.List;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.AuditLogDTO;
import com.jira.dto.ProjectDTO;
import com.jira.entities.AuditLog;
import com.jira.entities.Department;
import com.jira.entities.Project;
import com.jira.entities.Users;
import com.jira.exception.ResourceNotFoundException;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.CurrentUserService;
import com.jira.payloads.EntityType;
import com.jira.payloads.ProjectStatus;
import com.jira.repository.AuditLogRepository;
import com.jira.repository.DepartmentRepo;
import com.jira.repository.ProjectRepository;
import com.jira.service.AuditLogService;
import com.jira.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	DepartmentRepo departmentRepo;
	
	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	AuditLogService auditLogService;

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		// TODO Auto-generated method stub
		
		Project project = mapper.map(projectDTO, Project.class);
		
		Project savedProject = projectRepository.save(project);
		
		
		
		AuditLogDTO auditLogDTO = new AuditLogDTO();
		Users currentUser = currentUserService.getCurrentUser();
		auditLogDTO.setUserId(currentUser.getUserId());
		auditLogDTO.setAction(ActivityAction.CREATE);
		auditLogDTO.setEntityType(EntityType.PROJECT);
		auditLogDTO.setEntityId(projectDTO.getProjectId());
		auditLogDTO.setDescription(projectDTO.getDescription());
		
		auditLogService.createLogs(auditLogDTO);
		
		return mapper.map(savedProject, ProjectDTO.class);
	}

	@Override
	public ProjectDTO getProjectById(Integer projectId) {
		
		Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found with id :"+projectId));
		return mapper.map(project, ProjectDTO.class);
	}

	@Override
	public List<ProjectDTO> getAllProject() {
		// TODO Auto-generated method stub
		
		List<Project> projects = projectRepository.findAll();
		
		List<ProjectDTO> listOfProjectDtos = projects.stream().map(project->mapper.map(project, ProjectDTO.class)).toList();
		
		return listOfProjectDtos;
	}

	@Override
	public boolean deleteProjectById(Integer projectId) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found with id :"+projectId));
		
		if (project!=null) {
			projectRepository.delete(project);
			

			AuditLogDTO auditLogDTO = new AuditLogDTO();
			Users currentUser = currentUserService.getCurrentUser();
			auditLogDTO.setUserId(currentUser.getUserId());
			auditLogDTO.setAction(ActivityAction.DELETE);
			auditLogDTO.setEntityType(EntityType.PROJECT);
			auditLogDTO.setEntityId(project.getProjectId());
			auditLogDTO.setDescription(project.getDescription());
			
			auditLogService.createLogs(auditLogDTO);
			
			flag = true;
		}
		
		return flag;
	}

	@Override
	public List<ProjectDTO> getProjectByStatus(ProjectStatus projectStatus) {
		// TODO Auto-generated method stub
		List<Project> projectWithStatus = projectRepository.findBystatus(projectStatus);
		List<ProjectDTO> projectDto  = projectWithStatus.stream().map(project->mapper.map(project, ProjectDTO.class)).toList();
		return projectDto;
	}

	@Override
	public ProjectDTO assignProjectToDepartment(Integer projectId, Integer deptId) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findById(projectId).orElseThrow(()->new ResourceNotFoundException("Project not found with id :"+projectId));
		Department department = departmentRepo.findById(deptId).orElseThrow(()->new ResourceNotFoundException("Department not found with id :"+deptId));
		
		if (project!=null && department!=null) {
			project.setDepartment(department);
			Project savedProject = projectRepository.save(project);
			

			AuditLogDTO auditLogDTO = new AuditLogDTO();
			Users currentUser = currentUserService.getCurrentUser();
			auditLogDTO.setUserId(currentUser.getUserId());
			auditLogDTO.setAction(ActivityAction.ASSIGN);
			auditLogDTO.setEntityType(EntityType.PROJECT);
			auditLogDTO.setEntityId(project.getProjectId());
			auditLogDTO.setDescription(project.getDescription());
			
			auditLogService.createLogs(auditLogDTO);
			
			return mapper.map(savedProject, ProjectDTO.class);
		}
		
		return null;
	}

}
