package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20) @Builder.Default
    private String rol = "ROLE_USER";

    @Column(nullable = false) @Builder.Default
    private Boolean activo = true;
}
