package com.jira.service;

import java.util.List;

import com.jira.dto.TaskDto;

public interface TaskService {
	
	public TaskDto createTask(TaskDto taskDto);
	public List<TaskDto> getAllTask();
	public TaskDto getTaskById(Integer taskId);
	public boolean deleteTaskById(Integer taskId);
	

}
