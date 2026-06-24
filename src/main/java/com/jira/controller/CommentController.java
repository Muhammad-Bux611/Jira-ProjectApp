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

import com.jira.dto.CommentDTO;
import com.jira.entities.Comment;
import com.jira.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/")
	public ResponseEntity<CommentDTO> creates(@RequestBody CommentDTO commentDTO){
		CommentDTO comments = commentService.createComments(commentDTO);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(comments);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CommentDTO>> getAll(){
		List<CommentDTO> getAllComments = commentService.getAllComments();
		return ResponseEntity.status(HttpStatus.FOUND).body(getAllComments); 
		
	}

	@GetMapping("/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable Integer commentId ){
		
		CommentDTO commentDTO = commentService.getCommentById(commentId);
		
		return ResponseEntity.status(203).body(commentDTO);
		
	}
	
	@DeleteMapping("/{commentId}")
	public ResponseEntity<?> removedCommentFromTask(@PathVariable Integer commentId){
		
		boolean isRemoved = commentService.deleteCommentById(commentId);
		
		if (isRemoved) {
			return ResponseEntity.status(HttpStatus.OK).body("The comment is deleted successfully");
		}else {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment is not deleted .. something went to wrong");
		}
		
	}
	
	@GetMapping("/tasks/{taskId}")
	public ResponseEntity<List<CommentDTO>> getAllCommentsForSpecificTask(@PathVariable Integer taskId){
		
		List<CommentDTO> allCommentByTask = commentService.getAllCommentByTask(taskId);
		
		return ResponseEntity.status(HttpStatus.OK).body(allCommentByTask);
		
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<CommentDTO>> getAllCommentsForSpecificUser(@PathVariable Integer userId){
		
		List<CommentDTO> allCommentByTask = commentService.getAllCommentsByUser(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(allCommentByTask);
		
	}

	
	@GetMapping("/users/{userId}/task/{taskId}")
	public ResponseEntity<CommentDTO> getCommentForSpecificUserForSpecificPerson(@PathVariable Integer userId,@PathVariable Integer taskId){
		
		CommentDTO commentDTO = commentService.getCommentForUserForTask(userId, taskId);
		
		return ResponseEntity.status(HttpStatus.OK).body(commentDTO);
		
	}
}
