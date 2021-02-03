package com.codevallsma.loginTemplate.repositories;
import com.codevallsma.loginTemplate.model.Category;
import com.codevallsma.loginTemplate.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByCategoria(String nomCategoria);
	List<Category> findByCategoriaIn(Set<String> categoria);
}
