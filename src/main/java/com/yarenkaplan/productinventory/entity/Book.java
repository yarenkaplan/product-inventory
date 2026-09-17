package com.yarenkaplan.productinventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 chars.")
    @NotBlank
    @Column(name = "title")
    private String title;

    @Size(min = 2, max = 100, message = "Author name must be between 2 and 100 chars.")
    @NotBlank
    @Column(name = "author")
    private String author;

    //must be unique
    @NotNull
    @Column(name = "isbn", unique = true)
    private Long isbn;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock")
    private Integer stock;
}
