package com.jira.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Department {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer deptId;
	private String deptName;
	private String deptDescription;
	
	@OneToMany(mappedBy = "department")
	List<Users> users;
	
	@OneToMany(mappedBy = "department")
	List<Project> projects;
	
	
}
