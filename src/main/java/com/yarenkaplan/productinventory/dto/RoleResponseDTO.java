package com.yarenkaplan.productinventory.dto;

import com.yarenkaplan.productinventory.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleResponseDTO {
    private Long id;
    private RoleEnum name;
}
