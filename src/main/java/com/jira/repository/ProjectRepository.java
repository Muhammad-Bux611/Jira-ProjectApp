package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jira.entities.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
