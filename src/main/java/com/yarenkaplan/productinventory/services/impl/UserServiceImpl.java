package com.yarenkaplan.productinventory.services.impl;

import com.yarenkaplan.productinventory.dto.UserResponseDTO;
import com.yarenkaplan.productinventory.entity.Role;
import com.yarenkaplan.productinventory.entity.User;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.errors.AccessDeniedException;
import com.yarenkaplan.productinventory.errors.UserNotFoundException;
import com.yarenkaplan.productinventory.repository.RoleRepository;
import com.yarenkaplan.productinventory.repository.UserRepository;
import com.yarenkaplan.productinventory.requests.user.CreateUserRequest;
import com.yarenkaplan.productinventory.requests.user.UpdateUserRequest;
import com.yarenkaplan.productinventory.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setSurname(createUserRequest.getSurname());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        if (createUserRequest.getRole() != null) {
            Role role = roleRepository.findByName(createUserRequest.getRole()).orElseThrow(() -> new RuntimeException("Role not found " + createUserRequest.getRole()));
            user.setRole(role);
        }

        userRepository.save(user);
    }

    @Override
    public void updateUser(Long id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        //name
        if (updateUserRequest.getName() != null && !user.getName().equals(updateUserRequest.getName()) && !updateUserRequest.getName().trim().isBlank()) {
            user.setName(updateUserRequest.getName());
        }

        //surname
        if (updateUserRequest.getSurname() != null && !user.getSurname().equals(updateUserRequest.getSurname()) && !updateUserRequest.getSurname().trim().isBlank()) {
            user.setSurname(updateUserRequest.getSurname());
        }

        //email
        if (updateUserRequest.getEmail() != null && !user.getEmail().equals(updateUserRequest.getEmail()) && !updateUserRequest.getEmail().trim().isBlank()) {

            user.setEmail(updateUserRequest.getEmail());
        }

        //password
        if (updateUserRequest.getPassword() != null && !user.getPassword().equals(updateUserRequest.getPassword()) && !updateUserRequest.getPassword().trim().isBlank()) {
            user.setPassword(updateUserRequest.getPassword());
        }

        //role
        if (updateUserRequest.getRole() != null && !user.getRole().equals(updateUserRequest.getRole())) {
            Role role = roleRepository.findByName(updateUserRequest.getRole()).orElseThrow(() -> new RuntimeException("Role not found!"));
            user.setRole(role);
        }

        userRepository.save(user);
    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        return mapToResponseDTO(user);
    }

    @Override
    public Page<UserResponseDTO> findAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(this::mapToResponseDTO);
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        Role role = roleRepository.findByName(user.getRole().getName()).orElseThrow();

        return new UserResponseDTO(user.getId(), user.getName(), user.getSurname(), user.getEmail(), role.getName());
    }


    @Override
    public void deleteUserById(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepository.findByEmail(username).orElseThrow();
        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new AccessDeniedException();
        }

        userRepository.deleteById(id);
    }

    @Override
    public void deleteAllUsers() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User user = userRepository.findByEmail(username).orElseThrow();
        if (user.getRole().getName() != RoleEnum.ADMIN) {
            throw new AccessDeniedException();
        }

        userRepository.deleteAll();
    }
}
