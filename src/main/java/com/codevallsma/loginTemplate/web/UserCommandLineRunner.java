package com.codevallsma.loginTemplate.web;

import com.codevallsma.loginTemplate.TomTomApi.ApiCalls.RestAPIController;
import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.model.Role;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.CategoryRepository;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import com.codevallsma.loginTemplate.repositories.RoleRepository;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.lang.Thread.sleep;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;

    public UserCommandLineRunner(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository repository, RoleRepository roleRepository, RestaurantRepository restaurantRepository, CategoryRepository categoryRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = repository;
        this.roleRepository = roleRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        if(roleRepository.findByName("USER")== null) {
            //Save roles
            Role role1 = new Role("ADMIN", "Admin role");
            Role role2 = new Role("USER", "User role");
            Role role3 = new Role("OPERATIONAL", "Operational role");
            roleRepository.save(role1);
            roleRepository.save(role2);
            roleRepository.save(role3);
        }
        Role userRole = roleRepository.findByName("USER");
        Role adminRole = roleRepository.findByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        //user with user role
        addNewUser("codevallsma", "myUserPassword", roles);
        roles.add(adminRole);
        //user and admin roles
        addNewUser("adminUser", "myAdminPassword", roles);

        // data tomtotm api
        /*List<Restaurant> restaurants = (List<Restaurant>) restaurantRepository.findByRestaurantNameIsNull();
        RestAPIController.getInstance().setRestaurantRepository(restaurantRepository);
        RestAPIController.getInstance().setCategoryRepository(categoryRepository);
        for (Restaurant restaurant:
             restaurants) {
            RestAPIController.getInstance().getNearestRestaurant(restaurant, "1");
            sleep(500);
        }*/
    }
    public void addNewUser(String username, String password, Set<Role> roles ){
        //si l'usuari no està creat el guardem
        if(userRepository.findByUsername(username)==null){
            User userToSave = new User(username, bCryptPasswordEncoder.encode(password));
            userRepository.save(userToSave);
            userToSave.setRoles(roles);
            userRepository.save(userToSave);
        }
    }
}