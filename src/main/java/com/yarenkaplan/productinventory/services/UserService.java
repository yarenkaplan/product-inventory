package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.UserResponseDTO;
import com.yarenkaplan.productinventory.requests.user.CreateUserRequest;
import com.yarenkaplan.productinventory.requests.user.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface UserService {
    //CRUD
    void createUser(CreateUserRequest createUserRequest);

    void updateUser(Long id, UpdateUserRequest updateUserRequest);

    UserResponseDTO getCurrentUser(String email);

    UserResponseDTO findUserById(Long id);

    Page<UserResponseDTO> findAllUsers(Pageable pageable);

    void deleteUserById(Long id);

    void deleteAllUsers();
}
