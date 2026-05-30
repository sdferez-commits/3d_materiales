package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "articulos")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Articulo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false, length = 50)
    private String categoria;

    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(0)
    @Column(name = "stock_minimo", nullable = false)
    private Integer stockMinimo;

    @Column(length = 100)
    private String ubicacion;

    @Column(nullable = false) @Builder.Default
    private Boolean activo = true;

    @Transient
    public String getEstadoStock() {
        if (stock == 0)          return "CRITICO";
        if (stock < stockMinimo) return "BAJO";
        return "NORMAL";
    }

    @Transient
    public int getPorcentajeStock() {
        return Math.min(100, (int)((stock / (double) Math.max(stockMinimo * 2, 1)) * 100));
    }
}
