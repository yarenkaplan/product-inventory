package com.yarenkaplan.productinventory.services.auth.impl;

import com.yarenkaplan.productinventory.entity.Role;
import com.yarenkaplan.productinventory.entity.User;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.repository.RoleRepository;
import com.yarenkaplan.productinventory.repository.UserRepository;
import com.yarenkaplan.productinventory.requests.security.RegisterRequest;
import com.yarenkaplan.productinventory.services.auth.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(RegisterRequest registerRequest) {
        Role role = roleRepository.findByName(RoleEnum.USER).orElseThrow(() -> new RuntimeException("Role not found!"));

        User user = new User();
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setEmail(registerRequest.getEmail());

        String password = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(password);

        user.setRole(role);

        userRepository.save(user);
    }
}
