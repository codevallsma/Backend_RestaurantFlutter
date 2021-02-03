package com.codevallsma.loginTemplate.repositories;
import com.codevallsma.loginTemplate.model.Restaurant;
import com.codevallsma.loginTemplate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
