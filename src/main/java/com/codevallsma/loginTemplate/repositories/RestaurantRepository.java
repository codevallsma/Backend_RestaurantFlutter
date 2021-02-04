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
    @Query( value = "SELECT * FROM TERRASSA AS t JOIN Category as c JOIN Terrassa_Category as tc on c.id= tc.CATEGORY_ID AND t.id = tc.RESTAURANT_ID where c.categoria= ?1", nativeQuery = true)
    List<Restaurant> getRestaurantByCategoria(String categoryName);

    @Query( value = "SELECT * FROM TERRASSA AS t where c.categoria LIKE \'%?1\'", nativeQuery = true)
    List<Restaurant> getRestaurantAutcomplete(String categoryName);

    List<Restaurant> findRestaurantsByRestaurantNameStartingWith(String StartingQuery);

    List<Restaurant> findTop20ByRestaurantNameStartingWith(String restaurant);

    @Query(value = "SELECT * from TERRASSA as t order by st_distance_sphere( POINT( ?1, ?2) , POINT(t.LATITUD, t.LONGITUD )) LIMIT ?3",nativeQuery = true)
    List<Restaurant> getKnearestRestaurants(double latUser, double longUser, int limit);
}
