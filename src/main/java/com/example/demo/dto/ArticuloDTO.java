package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ArticuloDTO {
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    @Size(max = 20)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "El stock es obligatorio")
    @Min(0)
    private Integer stock;

    @NotNull(message = "El stock mínimo es obligatorio")
    @Min(0)
    private Integer stockMinimo;

    @Size(max = 100)
    private String ubicacion;
}
