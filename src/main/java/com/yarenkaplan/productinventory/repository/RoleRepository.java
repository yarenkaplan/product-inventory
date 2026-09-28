package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Role;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleEnum name);
}
