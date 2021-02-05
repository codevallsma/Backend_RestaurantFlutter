package com.codevallsma.loginTemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestaurantApplication {
	//private UserServiceImpl userService;
	//private BCryptPasswordEncoder bCryptPasswordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(RestaurantApplication.class, args);
	}
	/*@Bean
	CommandLineRunner runner() {
		return args -> {
			// username: user password: user
			userService.save(new User("user", bCryptPasswordEncoder.encode("password1")
					"$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi",
					"USER"));
			// username: admin password: admin
			userService.save(new User("admin",
					"$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG",
					"ADMIN"));
		};
	}*/
}
