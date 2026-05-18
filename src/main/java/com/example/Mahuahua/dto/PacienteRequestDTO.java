package com.example.Mahuahua.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record PacienteRequestDTO(
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "Los apellidos son obligatorios") String apellidos,
    @Email(message = "El email debe ser válido") @NotBlank(message = "El email es obligatorio") String email,
    String telefono,
    LocalDate fechaNacimiento
) {}
