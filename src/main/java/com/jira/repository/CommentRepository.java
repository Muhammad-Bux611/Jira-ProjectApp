package com.jira.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	
	List<Comment> findByTaskTaskId(Integer taskId);
	List<Comment> findByUsersUserId(Integer userId);
	Optional<Comment> findByUsersUserIdAndTaskTaskId(Integer userId, Integer taskId);

}
