package com.jira.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.Task;

public interface TaskRepository  extends JpaRepository<Task, Integer>{

}
