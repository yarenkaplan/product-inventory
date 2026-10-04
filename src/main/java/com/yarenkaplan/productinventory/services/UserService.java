package com.yarenkaplan.productinventory.services;

import com.yarenkaplan.productinventory.dto.UserResponseDTO;
import com.yarenkaplan.productinventory.requests.user.CreateUserRequest;
import com.yarenkaplan.productinventory.requests.user.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //CRUD
    void createUser(CreateUserRequest createUserRequest);

    void updateUser(Long id, UpdateUserRequest updateUserRequest);

    UserResponseDTO findUserById(Long id);

    Page<UserResponseDTO> findAllUsers(Pageable pageable);

    void deleteUserById(Long id);

    void deleteAllUsers();
}
