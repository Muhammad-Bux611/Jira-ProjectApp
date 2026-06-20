package com.jira.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Setter
@Getter
public class AuthRequest {

	private String username;
	private String password;
	
}
