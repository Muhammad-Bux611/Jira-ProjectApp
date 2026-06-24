package com.jira.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.dto.CommentDTO;
import com.jira.entities.Comment;
import com.jira.exception.ResourceNotFoundException;
import com.jira.repository.CommentRepository;
import com.jira.service.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ModelMapper mapper;

	@Override
	public CommentDTO createComments(CommentDTO commentDTO) {
		Comment comment = mapper.map(commentDTO, Comment.class);
		Comment savedComment = commentRepository.save(comment);
		return mapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public CommentDTO getCommentById(Integer commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment not found "+commentId));
		
		return mapper.map(comment, CommentDTO.class);
	}

	@Override
	public List<CommentDTO> getAllComments() {
		
		List<Comment> listOfComment = commentRepository.findAll();
		List<CommentDTO> comments = listOfComment.stream().map(comment->mapper.map(comment, CommentDTO.class)).toList();
		
		if (comments.isEmpty()) {
			throw new ResourceNotFoundException("Not any user has commented yet");
		}
		
		return comments;
	}

	@Override
	public boolean deleteCommentById(Integer commentId) {
		
		boolean isRemoved = false;
		
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment not found "+commentId));
		
		if (comment!=null) {
			
			commentRepository.delete(comment);
			
			isRemoved = true;
		}
		
		return isRemoved;
	}

	@Override
	public List<CommentDTO> getAllCommentByTask(Integer taskId) {
		
		List<Comment> listOfComment = commentRepository.findByTaskTaskId(taskId);
		
		List<CommentDTO> commentDtos = listOfComment.stream().map(comment->mapper.map(comment, CommentDTO.class)).toList();
		
		if (commentDtos.isEmpty()) {
			throw new ResourceNotFoundException("Not any user has commented in this task yet");

		}
		
		return commentDtos;
	}

	@Override
	public List<CommentDTO> getAllCommentsByUser(Integer userId) {
		
		List<Comment> listOfComment = commentRepository.findByUsersUserId(userId);
		
		
		List<CommentDTO> commentDtos = listOfComment.stream().map(comment->mapper.map(comment, CommentDTO.class)).toList();
		
		if (commentDtos.isEmpty()) {
			throw new ResourceNotFoundException("Not any user has commented in this task yet");

		}
		
		return commentDtos;
		
		
	}

	@Override
	public CommentDTO getCommentForUserForTask(Integer userId, Integer taskId) {

		Comment comment = commentRepository.findByUsersUserIdAndTaskTaskId(userId, taskId)
				.orElseThrow(()->new ResourceNotFoundException("not user with id :"+userId+" is commented on task that contain id "+taskId))
				;
		
		
		return mapper.map(comment, CommentDTO.class);
	}

}
