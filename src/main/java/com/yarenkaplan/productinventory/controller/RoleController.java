package com.yarenkaplan.productinventory.controller;

import com.yarenkaplan.productinventory.dto.RoleResponseDTO;
import com.yarenkaplan.productinventory.entity.User;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.errors.AccessDeniedException;
import com.yarenkaplan.productinventory.requests.role.CreateRoleRequest;
import com.yarenkaplan.productinventory.services.RoleService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public void createRole(@Valid @RequestBody CreateRoleRequest createRoleRequest) {
        roleService.createRole(createRoleRequest);
    }

    @GetMapping("/findAllRoles")
    public List<RoleResponseDTO> findAllRoles() {
        return roleService.findAllRoles();
    }

    @GetMapping("/findRoleById/{id}")
    public Optional<RoleResponseDTO> findRoleById(@PathVariable Long id) {
        return roleService.findRoleById(id);
    }

    @DeleteMapping("{id}")
    public void deleteRoleById(@PathVariable Long id) {
        roleService.deleteRoleById(id);
    }
}
