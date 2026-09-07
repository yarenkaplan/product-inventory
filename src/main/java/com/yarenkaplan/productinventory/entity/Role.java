package com.yarenkaplan.productinventory.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name ="name")
    private String name;

    @Column(name ="description")
    private String description;

    @CreatedDate
    @Column(name ="createdAt")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
