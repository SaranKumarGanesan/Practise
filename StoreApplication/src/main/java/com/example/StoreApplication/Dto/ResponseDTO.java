package com.example.StoreApplication.Dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {

	private String message;
	private String session;
	
	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String message, String session) {
		super();
		this.message = message;
		this.session = session;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
	
	

}
