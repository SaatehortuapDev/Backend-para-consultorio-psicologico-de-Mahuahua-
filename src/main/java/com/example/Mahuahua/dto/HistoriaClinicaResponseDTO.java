package com.example.Mahuahua.dto;

import java.time.LocalDateTime;
import java.util.List;

public record HistoriaClinicaResponseDTO(
    Long id,
    LocalDateTime fechaCreacion,
    PacienteResponseDTO paciente,
    List<EvolucionResponseDTO> evoluciones
) {}
