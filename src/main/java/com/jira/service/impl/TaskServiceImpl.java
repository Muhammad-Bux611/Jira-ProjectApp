package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.AuditLogDTO;
import com.jira.dto.TaskDto;
import com.jira.entities.Task;
import com.jira.exception.ResourceNotFoundException;
import com.jira.payloads.ActivityAction;
import com.jira.payloads.CurrentUserService;
import com.jira.payloads.EntityType;
import com.jira.repository.TaskRepository;
import com.jira.service.AuditLogService;
import com.jira.service.TaskService;
@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	CurrentUserService currentUserService;
	
	@Autowired
	AuditLogService auditLogService;

	@Override
	public TaskDto createTask(TaskDto taskDto) {
		// TODO Auto-generated method stub
		
		Task task = mapper.map(taskDto, Task.class);
		
		Task savedTask = taskRepository.save(task);
		
		AuditLogDTO auditLogDTO = new AuditLogDTO();
		auditLogDTO.setAction(ActivityAction.CREATE);
		auditLogDTO.setEntityType(EntityType.TASK);
		auditLogDTO.setEntityId(savedTask.getTaskId());
		auditLogDTO.setUserId(currentUserService.getCurrentUser().getUserId());
		auditLogDTO.setDescription("task "+savedTask.getTitle()+" is created");
		
		
		auditLogService.createLogs(auditLogDTO);
		
		
		return mapper.map(savedTask, TaskDto.class);
	}

	@Override
	public List<TaskDto> getAllTask() {
		// TODO Auto-generated method stub
		
		List<Task> allTask = taskRepository.findAll();
		
		List<TaskDto> listOfTaskDto = allTask.stream().map(task->mapper.map(task, TaskDto.class)).toList();
		
		return listOfTaskDto;
	}

	@Override
	public TaskDto getTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		
		Task task = taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task is not found with id :"+taskId));
		
		return mapper.map(task, TaskDto.class);
	}

	@Override
	public boolean deleteTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		boolean isRemoved= false;
		Task task = taskRepository.findById(taskId).orElseThrow(()->new ResourceNotFoundException("Task is not found with id :"+taskId));
		
		if (task!=null) {
		
			taskRepository.delete(task);
			

			AuditLogDTO auditLogDTO = new AuditLogDTO();
			auditLogDTO.setAction(ActivityAction.CREATE);
			auditLogDTO.setEntityType(EntityType.TASK);
			auditLogDTO.setEntityId(task.getTaskId());
			auditLogDTO.setUserId(currentUserService.getCurrentUser().getUserId());
			auditLogDTO.setDescription("task "+task.getTitle()+" is created");
			
			
			auditLogService.createLogs(auditLogDTO);
			
			
			isRemoved = true;
			
		}
		
		return isRemoved;
	}

}
