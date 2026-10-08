package com.yarenkaplan.productinventory.requests.role;

import com.yarenkaplan.productinventory.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateRoleRequest {

    @NotNull(message = "Role name cannot be null!")
    private RoleEnum name;
}
