package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.Services.UserServiceImpl;
import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.mapper.UserMapper;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import com.codevallsma.loginTemplate.web.presentation.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserService userService;
	private UserServiceImpl userServiceImpl;

	public RegistrationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceImpl userServiceImpl) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userServiceImpl = userServiceImpl;
	}
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody RegistrationRequest userRequest) {
		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		User parsedUser = UserMapper.toRegistrationDomain(userRequest);
		final User userToSave = userServiceImpl.save(parsedUser);

		return new ResponseEntity<>(userToSave, HttpStatus.OK);
	}
}
