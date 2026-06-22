package com.jira.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.entities.Project;
import com.jira.payloads.ProjectStatus;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
	
	public List<Project> findBystatus(ProjectStatus projectStatus);

}
