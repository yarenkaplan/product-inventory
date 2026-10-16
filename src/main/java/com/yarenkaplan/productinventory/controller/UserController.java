package com.yarenkaplan.productinventory.controller;

import com.yarenkaplan.productinventory.dto.UserResponseDTO;
import com.yarenkaplan.productinventory.entity.User;
import com.yarenkaplan.productinventory.enums.RoleEnum;
import com.yarenkaplan.productinventory.requests.user.CreateUserRequest;
import com.yarenkaplan.productinventory.requests.user.UpdateUserRequest;
import com.yarenkaplan.productinventory.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        userService.updateUser(id, updateUserRequest);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @GetMapping
    public Page<UserResponseDTO> findAllUsers(Pageable pageable) {
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();

        return ResponseEntity.ok(userService.getCurrentUser(email));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @DeleteMapping("/deleteAllUsers")
    public void deleteAllUsers() {
        userService.deleteAllUsers();
    }
}