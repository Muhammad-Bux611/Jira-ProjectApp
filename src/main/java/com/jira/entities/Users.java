package com.jira.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String firstName;
	private String email;
	private String password;
	private LocalDateTime dateTime;
	
	@ManyToOne
	private Role role;
	@ManyToOne
	private Department department;
	
	@OneToMany(mappedBy = "users")
	private List<ProjectMember> projectMember;
	
	@OneToMany(mappedBy = "users")
	private List<Comment> comment;
	
}
