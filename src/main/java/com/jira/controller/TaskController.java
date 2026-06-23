package com.jira.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jira.dto.TaskDto;
import com.jira.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	
	@Autowired
	private TaskService taskService;
	@PostMapping("/")
	public ResponseEntity<TaskDto> create(@RequestBody TaskDto taskDto)
	{
		TaskDto task = taskService.createTask(taskDto);
		return  ResponseEntity.status(HttpStatus.CREATED).body(task);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<TaskDto>> getAll(){
		List<TaskDto> allTask = taskService.getAllTask();
		
		return ResponseEntity.status(HttpStatus.OK).body(allTask);
	}
	
	@GetMapping("/{taskId}")
	public ResponseEntity<TaskDto> findTaskById(@PathVariable Integer taskId){
		
		return ResponseEntity.status(HttpStatus.FOUND).body(taskService.getTaskById(taskId));
	}
	
	@DeleteMapping("/{taskId}")
	public ResponseEntity<?> removeTask(@PathVariable Integer taskId){
		
		boolean isRemoved = taskService.deleteTaskById(taskId);
		
		if (isRemoved) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task is successfully deleted");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Task is not deleted !! something went to wrong");
		}
	}
}
