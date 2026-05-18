package com.example.Mahuahua.dto;

import java.time.LocalDate;

public record PacienteResponseDTO(
    Long id,
    String nombre,
    String apellidos,
    String email,
    String telefono,
    LocalDate fechaNacimiento
) {}
