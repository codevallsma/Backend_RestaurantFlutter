package com.codevallsma.loginTemplate.web.Controllers;

import com.codevallsma.loginTemplate.model.Category;
import com.codevallsma.loginTemplate.model.User;
import com.codevallsma.loginTemplate.repositories.CategoryRepository;
import com.codevallsma.loginTemplate.repositories.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CategoryController {
    private RestaurantRepository restaurantRepository;
    private CategoryRepository categoryRepository;

    public CategoryController(RestaurantRepository restaurantRepository, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
    }

    @PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
    @GetMapping("/category")
    public ResponseEntity<List<Category>> getUserAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }
}
