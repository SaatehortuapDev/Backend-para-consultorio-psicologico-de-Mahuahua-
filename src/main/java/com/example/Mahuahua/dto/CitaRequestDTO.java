package com.example.Mahuahua.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record CitaRequestDTO(
    @NotNull(message = "El id del paciente es obligatorio") Long pacienteId,
    @NotNull(message = "El id del psicólogo es obligatorio") Long psicologoId,
    @NotNull(message = "La fecha y hora son obligatorias") LocalDateTime fechaHora,
    @NotBlank(message = "El motivo de la consulta es obligatorio") String motivo
) {}
