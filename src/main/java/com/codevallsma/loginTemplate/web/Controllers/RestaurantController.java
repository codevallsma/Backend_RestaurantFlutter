package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class RestaurantController {
    private RestaurantRepository restaurantRepository;

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> getUserByUsername(@RequestParam(name = "category", required = true) String category) {

        List<Restaurant> restaurantList= null;
        if(category.compareTo("null")!=0) {
            //Object restaurantList2 =restaurantRepository.findByCategoria_restaurant(category);
            System.out.println("");
        }
        if (restaurantList == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not an acceptble value"
            );
        }
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

}
