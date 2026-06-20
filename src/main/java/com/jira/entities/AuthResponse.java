package com.jira.entities;

import org.springframework.http.ResponseEntity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Setter
@Getter
public class AuthResponse {

	
	private String massage="user login";
	private ResponseEntity<String> response=ResponseEntity.ok("Successfully");
	
}
