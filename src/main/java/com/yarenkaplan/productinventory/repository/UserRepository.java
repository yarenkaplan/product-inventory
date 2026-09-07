package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
