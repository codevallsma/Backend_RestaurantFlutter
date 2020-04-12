package com.codevallsma.loginTemplate.web.presentation;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String name;

	public UserResponse(long id, String name) {
		this.id = id;
		this.name = name;
	}
}
