package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email=:email")
    Optional<User> findByEmail(String email);
}