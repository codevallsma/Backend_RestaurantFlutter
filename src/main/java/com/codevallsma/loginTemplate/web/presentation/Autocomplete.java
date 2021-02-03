package com.codevallsma.loginTemplate.web.presentation;

import com.codevallsma.loginTemplate.model.Restaurant;

import java.io.Serializable;

public class Autocomplete implements Serializable {
    private long restaurantId;
    private String restaurantName;

    public Autocomplete(int restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
    }

    public Autocomplete(Restaurant restaurant) {
        this.restaurantId = restaurant.getId();
        this.restaurantName = restaurant.getRestaurantName();

    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
