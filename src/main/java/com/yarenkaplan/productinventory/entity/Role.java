package com.yarenkaplan.productinventory.entity;

import com.yarenkaplan.productinventory.enums.RoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name ="name", nullable = false, unique = true)
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private RoleEnum name;

    @CreatedDate
    @Column(name ="createdAt",nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
