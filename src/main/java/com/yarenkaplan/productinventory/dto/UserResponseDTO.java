package com.yarenkaplan.productinventory.dto;

import com.yarenkaplan.productinventory.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private RoleEnum role;
}
