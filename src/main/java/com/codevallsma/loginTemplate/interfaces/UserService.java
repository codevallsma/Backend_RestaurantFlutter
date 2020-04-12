package com.codevallsma.loginTemplate.interfaces;

import com.codevallsma.loginTemplate.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	User getUser(long id);

	User save(User user);
}
