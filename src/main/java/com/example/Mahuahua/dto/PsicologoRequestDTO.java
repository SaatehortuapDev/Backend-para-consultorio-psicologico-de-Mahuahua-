package com.example.Mahuahua.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PsicologoRequestDTO(
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "Los apellidos son obligatorios") String apellidos,
    @NotBlank(message = "La especialidad es obligatoria") String especialidad,
    @Email(message = "El email debe ser válido") @NotBlank(message = "El email es obligatorio") String email
) {}
