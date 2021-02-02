package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.Services.UserServiceImpl;
import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.mapper.UserMapper;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import com.codevallsma.loginTemplate.web.presentation.RegistrationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RegistrationController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserRepository userRepository;
	private UserServiceImpl userServiceImpl;

	public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceImpl userServiceImpl) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userServiceImpl = userServiceImpl;
		this.userRepository = userRepository;
	}
	@PostMapping("/registration")
	public ResponseEntity<User> saveUser(@RequestBody RegistrationRequest userRequest) {
		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		User user = null;
		if(userRequest.getEmail()!=null && userRequest.getUserName()!=null){
			user = userRepository.findByUsernameOrEmail(userRequest.getEmail());
			if(user == null){
				user = userRepository.findByUsernameOrEmail(userRequest.getUserName());
			}
		} else  {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN, "Some fields are empty"
			);
		}
		if (user != null){
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN, "There's an existing user with those credentials"
			);
		}
		User parsedUser = UserMapper.toRegistrationDomain(userRequest);
		final User userToSave = userServiceImpl.saveUserWithRoles(parsedUser);

		return new ResponseEntity<>(userToSave, HttpStatus.OK);
	}
}
