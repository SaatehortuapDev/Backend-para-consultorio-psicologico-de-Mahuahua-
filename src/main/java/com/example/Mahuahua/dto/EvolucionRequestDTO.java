package com.example.Mahuahua.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EvolucionRequestDTO(
    @NotNull(message = "El id de la historia clínica es obligatorio") Long historiaClinicaId,
    Long citaId,
    @NotBlank(message = "Las notas son obligatorias") String notas,
    String diagnostico
) {}
