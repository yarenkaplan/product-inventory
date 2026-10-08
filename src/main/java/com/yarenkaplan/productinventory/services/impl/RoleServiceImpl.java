package com.yarenkaplan.productinventory.services.impl;

import com.yarenkaplan.productinventory.dto.RoleResponseDTO;
import com.yarenkaplan.productinventory.entity.Role;
import com.yarenkaplan.productinventory.entity.User;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.errors.AccessDeniedException;
import com.yarenkaplan.productinventory.errors.RoleNotFoundException;
import com.yarenkaplan.productinventory.repository.RoleRepository;
import com.yarenkaplan.productinventory.repository.UserRepository;
import com.yarenkaplan.productinventory.requests.role.CreateRoleRequest;
import com.yarenkaplan.productinventory.services.RoleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRole(CreateRoleRequest createRoleRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //user role must be admin
        String currentUserName = authentication.getName();
        User user = userRepository.findByEmail(currentUserName).orElseThrow();
        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new AccessDeniedException();
        }

        //role must be unique, first check if role exist
        if (!roleRepository.findByName(createRoleRequest.getName()).isEmpty()) {
            throw new AccessDeniedException();
        }

        Role role = new Role();
        role.setName(createRoleRequest.getName());

        roleRepository.save(role);
    }

    @Override
    public List<RoleResponseDTO> findAllRoles() {
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().map(this::mapToResponseDTO).toList();
    }

    private RoleResponseDTO mapToResponseDTO(Role role) {
        RoleResponseDTO roleResponseDTO = new RoleResponseDTO();
        roleResponseDTO.setId(role.getId());
        roleResponseDTO.setName(role.getName());

        return roleResponseDTO;
    }

    @Override
    public Optional<RoleResponseDTO> findRoleById(Long id) {
        return roleRepository.findById(id).map(this::mapToResponseDTO);
    }

    @Override
    public void deleteRoleById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //user role must be admin
        String currentUserName = authentication.getName();
        User user = userRepository.findByEmail(currentUserName).orElseThrow();
        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new AccessDeniedException();
        }

        Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        if (role.getName() == RoleEnum.ADMIN || role.getName() == RoleEnum.USER) {
            throw new AccessDeniedException();
        }

        roleRepository.deleteById(id);
    }
}
