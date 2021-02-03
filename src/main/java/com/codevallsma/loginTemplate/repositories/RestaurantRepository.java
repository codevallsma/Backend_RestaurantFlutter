package com.codevallsma.loginTemplate.repositories;
import com.codevallsma.loginTemplate.model.Category;
import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByRestaurantNameIsNull();
}
