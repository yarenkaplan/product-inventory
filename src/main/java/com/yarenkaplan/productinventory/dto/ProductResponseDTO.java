package com.yarenkaplan.productinventory.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yarenkaplan.productinventory.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private InventoryStatus status;
    private Long categoryId;
}
