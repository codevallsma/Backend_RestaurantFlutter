package com.codevallsma.loginTemplate.repositories;


import com.codevallsma.loginTemplate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByUsernameAndEmailAndPassword(String username, String email, String password);
    User findByEmail(String email);
    @Query("SELECT user FROM User user WHERE (user.username =:userNameOrEmail OR user.email =:userNameOrEmail) AND user.password =:password" )
    User findIfUserExists(@Param("userNameOrEmail") String userNameOrEmail, @Param("password") String password);
    Integer findAllById(Long id);
    User findById(long id);
    List<User> findAll();
}
