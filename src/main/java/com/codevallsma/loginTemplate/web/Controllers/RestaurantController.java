package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import com.codevallsma.loginTemplate.web.presentation.Autocomplete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@RestController
public class RestaurantController {
    private RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> getRestaurantByCategoria(@RequestParam(name = "category", required = true) String category) {

        List<Restaurant> restaurantList= null;
        if(category.compareTo("null")!=0) {
            restaurantList =restaurantRepository.getRestaurantByCategoria(category);
        }
        if (restaurantList == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not an acceptable value or value not found"
            );
        }
        return new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurant/autocomplete")
    public ResponseEntity<List<Autocomplete>> getAutocomplete(@RequestParam(name = "restaurantName", required = true) String restaurantName) {

        List<Restaurant> restaurantList= null;
        List<Autocomplete> autocompletes= null;
        if(restaurantName.compareTo("null")!=0) {
            HashMap<String, String> restaurantNameHash = new HashMap<String, String>();
            restaurantList =restaurantRepository.findTop20ByRestaurantNameStartingWith(restaurantName);
            if(restaurantList!=null) {
                autocompletes= new LinkedList<>();
                for (Restaurant restaurant :
                        restaurantList) {
                    if (!restaurantNameHash.containsKey(restaurant.getRestaurantName())) {
                        restaurantNameHash.put(restaurant.getRestaurantName(), restaurant.getRestaurantName());
                        autocompletes.add(new Autocomplete(restaurant));
                    }

                }
            }
        }
        if (restaurantList == null || autocompletes.size()==0) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not an acceptable value or value not found"
            );
        }
        return new ResponseEntity<>(autocompletes, HttpStatus.OK);
    }

}