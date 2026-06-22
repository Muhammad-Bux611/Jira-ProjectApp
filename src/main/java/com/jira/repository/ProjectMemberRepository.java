package com.jira.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.Project;
import com.jira.entities.ProjectMember;
import com.jira.entities.Users;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
	
//	Optional<List<ProjectMember>> findByUserUserId(Integer userId);
//	Optional<List<ProjectMember>> findByProjectsProjectId(Integer projectId);
	 boolean existsByProjectProjectIdAndUsersUserId(
	            Integer projectId,
	            Integer userId
	    );
	 
	 Optional<Users> findByUsersUserId(Integer userId);
	 Optional<Project> findByProjectProjectId(Integer projectId);
 
}
