package com.codevallsma.loginTemplate.Services;

import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

@Service("restaurantService")
public class RestaurantServiceImpl {
    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void getnNearestRestaurants(){

    }

}
