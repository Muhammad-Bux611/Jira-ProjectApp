package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.Department;

public interface DepartmentRepo  extends JpaRepository<Department, Integer>{

}
