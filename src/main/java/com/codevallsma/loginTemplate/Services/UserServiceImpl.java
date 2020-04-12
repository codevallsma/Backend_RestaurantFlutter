package com.codevallsma.loginTemplate.Services;


import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.mapper.UserDetailsMapper;
import com.codevallsma.loginTemplate.model.Role;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.RoleRepository;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

	private RoleRepository roleRepository;

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final User retrievedUser = userRepository.findByUsername(userName);
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}

	@Override
	public User getUser(long id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		Role userRole = roleRepository.findByName("USER");
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);

		User userToSave = new User(user.getUsername(), user.getPassword());
		userToSave.setRoles(roles);

		return userRepository.save(userToSave);
	}
}
