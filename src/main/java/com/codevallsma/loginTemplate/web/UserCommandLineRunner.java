package com.codevallsma.loginTemplate.web;

import com.codevallsma.loginTemplate.model.Role;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.RoleRepository;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserCommandLineRunner(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository repository, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        //Save roles
        Role role1 = new Role("ADMIN", "Admin role");
        Role role2 = new Role("USER", "User role");
        Role role3 = new Role("OPERATIONAL", "Operational role");
        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);

        Role userRole = roleRepository.findByName("USER");
        Role adminRole = roleRepository.findByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        //user with user role
        addNewUser("codevallsma", "myUserPassword", roles);
        roles.add(adminRole);
        //user and admin roles
        addNewUser("adminUser", "myAdminPassword", roles);

    }
    public void addNewUser(String username, String password, Set<Role> roles ){
        User userToSave = new User(username, bCryptPasswordEncoder.encode(password));
        userRepository.save(userToSave);
        userToSave.setRoles(roles);
        userRepository.save(userToSave);
    }
}