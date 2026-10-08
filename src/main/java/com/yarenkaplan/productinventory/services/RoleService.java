package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.RoleResponseDTO;
import com.yarenkaplan.productinventory.requests.role.CreateRoleRequest;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void createRole(CreateRoleRequest createRoleRequest);

    List<RoleResponseDTO> findAllRoles();

    Optional<RoleResponseDTO> findRoleById(Long id);

    void deleteRoleById(Long id);
}
