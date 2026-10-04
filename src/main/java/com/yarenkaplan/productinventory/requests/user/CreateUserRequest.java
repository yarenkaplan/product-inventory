package com.yarenkaplan.productinventory.requests.user;

import com.yarenkaplan.productinventory.enums.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @Size(min = 2, max = 100, message = "Surname must be between 2 and 100 characters")
    private String surname;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email must be valid!")
    private String email;

    @NotBlank
    private String password;

    private RoleEnum role;
}
