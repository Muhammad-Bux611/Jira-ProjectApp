package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jira.dto.ProjectDTO;
import com.jira.entities.Project;
import com.jira.repository.ProjectRepository;
import com.jira.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	ProjectRepository projectRepository;

	

	@Override
	public ProjectDTO createProject(ProjectDTO projectDTO) {
		// TODO Auto-generated method stub
		
		Project project = mapper.map(projectDTO, Project.class);
		
		Project savedProject = projectRepository.save(project);
		
		return mapper.map(savedProject, ProjectDTO.class);
	}

	@Override
	public ProjectDTO getProjectById(Integer projectId) {
		// TODO Auto-generated method stub
		
		Project project = projectRepository.findById(projectId).orElse(null);
		
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
		
		Project project = projectRepository.findById(projectId).orElse(null);
		
		if (project!=null) {
			projectRepository.delete(project);
			flag = true;
		}
		
		return flag;
	}

}
