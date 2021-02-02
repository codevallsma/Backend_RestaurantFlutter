package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.mapper.UserMapper;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import com.codevallsma.loginTemplate.web.presentation.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserService userService;
	private UserRepository userRepository;

	public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder,UserRepository userRepository) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userRepository = userRepository;
	}

	//@Secured("ROLE_ADMIN")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUser(@PathVariable long id) {
		final User user = userService.getUser(id);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		UserResponse userResponse = UserMapper.toResponse(user);
		return new ResponseEntity<>(userResponse, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody AuthorizationRequest userRequest) {
		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		final User userToSave = userService.save(UserMapper.toDomain(userRequest));

		return new ResponseEntity<>(userToSave, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
	@GetMapping("")
	public ResponseEntity<User> getUserByUsername(@RequestParam(name = "id") String username) {
		final User user =userRepository.findByUsername(username);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		UserResponse userResponse = UserMapper.toResponse(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
