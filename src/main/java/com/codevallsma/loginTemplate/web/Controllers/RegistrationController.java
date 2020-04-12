package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.mapper.UserMapper;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
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

	public RegistrationController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody AuthorizationRequest userRequest) {
		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		final User userToSave = userService.save(UserMapper.toDomain(userRequest));

		return new ResponseEntity<>(userToSave, HttpStatus.OK);
	}
}
