package com.jira.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.Project;
import com.jira.entities.ProjectMember;
import com.jira.entities.Users;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
	
	List<ProjectMember> findAllByProjectProjectId(Integer projectId);
	ProjectMember findByProjectProjectIdAndUsersUserId(Integer projectId,Integer userId);
	 boolean existsByProjectProjectIdAndUsersUserId(
	            Integer projectId,
	            Integer userId
	    );
	 
	 Optional<Users> findByUsersUserId(Integer userId);
	 Optional<Project> findByProjectProjectId(Integer projectId);
 
}
