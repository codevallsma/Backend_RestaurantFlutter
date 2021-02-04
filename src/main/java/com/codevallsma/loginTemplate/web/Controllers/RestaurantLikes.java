package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.mapper.UserMapper;
import com.codevallsma.loginTemplate.model.Category;
import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import com.codevallsma.loginTemplate.repositories.UserRepository;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class RestaurantLikes {
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public RestaurantLikes(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    /**
     * Gets the restaurants liked by a user given the category
     * @param userId
     * @param categoryID
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurantLikesCategory/{userID}")
    public ResponseEntity<List<Restaurant>> getRestaurantLikedByUserIdAndCategory(@PathVariable("userID") Long userId, @RequestParam(name = "categoryID", required = true) Long categoryID ) {
        List<Restaurant> restaurantRes = null;
        if(categoryID!=null) {
            restaurantRes =restaurantRepository.getLikedRestaurantsByCategory(userId, categoryID);
            if(restaurantRes==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an acceptable value or value not found");
            }
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an acceptable value or value not found");
        }
        return new ResponseEntity<>(restaurantRes, HttpStatus.OK);
    }

    /**
     * Gets the restaurants liked by the user
     * @param userId
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/restaurantLikes/{userID}")
    public ResponseEntity<List<Restaurant>> getRestaurantLikedByUserId(@PathVariable("userID") Long userId) {
        List<Restaurant> restaurantRes = null;
        if(userId!=null) {
            restaurantRes =restaurantRepository.getLikedRestaurants(userId);
            if(restaurantRes==null){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an acceptable value or value not found");
            }
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not an acceptable value or value not found");
        }
        return new ResponseEntity<>(restaurantRes, HttpStatus.OK);
    }

    /**
     * Posts the liked restaurant
     * @param userId
     * @param restaurantID
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @PostMapping("/restaurantLikes/{userID}/{restaurantID}")
    public ResponseEntity saveRestaurantLiked(@PathVariable("userID") Long userId,@PathVariable("restaurantID") Long restaurantID) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user;
        if(userOptional.isPresent()){
            user =userOptional.get();
            if(user.getRestaurantsLiked().stream().noneMatch(r -> r.getId() == restaurantID)){
                Optional<Restaurant> restaurant =restaurantRepository.findById(restaurantID);
                restaurant.ifPresent(value -> user.getRestaurantsLiked().add(value));
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a valid restaurant ID");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a valid user ID");
        }
        return ResponseEntity.ok().build();
    }

    /**
     * Posts the liked restaurant
     * @param userId
     * @param restaurantID
     * @return
     */
    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @DeleteMapping("/restaurantLikes/{userID}/{restaurantID}")
    public ResponseEntity deleteRestaurantLiked(@PathVariable("userID") Long userId,@PathVariable("restaurantID") Long restaurantID) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user;
        if(userOptional.isPresent()){
            user =userOptional.get();
            if(user.getRestaurantsLiked().stream().anyMatch(r -> r.getId() == restaurantID)){
                //we have to deleted
                user.getRestaurantsLiked().removeIf(e-> e.getId() == restaurantID);
                //persist the changes
                userRepository.save(user);
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a valid restaurant ID");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not a valid user ID");
        }
        return ResponseEntity.ok().build();
    }
}
