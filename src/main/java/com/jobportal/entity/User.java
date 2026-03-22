package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.*;

import com.jobportal.entity.Role;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    // this class will be directly create the tables in mysql...
    // this class is basically an entity
    // which will basically consist of description of table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt;
}
