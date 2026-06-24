package com.jira.service;

import java.util.List;

import com.jira.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComments(CommentDTO commentDTO);
	public CommentDTO getCommentById(Integer commentId);
	public List<CommentDTO> getAllComments();
	public boolean deleteCommentById(Integer commentId);
	public List<CommentDTO> getAllCommentByTask(Integer taskId);
	public List<CommentDTO> getAllCommentsByUser(Integer userId);
	public CommentDTO getCommentForUserForTask(Integer userId, Integer taskId);
	
}
