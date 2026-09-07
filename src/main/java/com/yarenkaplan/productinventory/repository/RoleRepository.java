package com.yarenkaplan.productinventory.repository;

import com.yarenkaplan.productinventory.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
