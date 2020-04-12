package com.codevallsma.loginTemplate.mapper;

import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import com.codevallsma.loginTemplate.web.presentation.UserResponse;

public class UserMapper {

	private UserMapper() {
	}

	public static UserResponse toResponse(User user) {
		return new UserResponse(user.getId(), user.getUsername());
	}

	public static User toDomain(AuthorizationRequest authorizationRequest) {
		return new User(authorizationRequest.getUserName(), authorizationRequest.getPassword());
	}
}
