package com.jira.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomExceptionResponse {
	
	private int status;
	private String message;

}
