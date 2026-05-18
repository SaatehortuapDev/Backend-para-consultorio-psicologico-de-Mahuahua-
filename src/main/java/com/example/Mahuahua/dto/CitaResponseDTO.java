package com.example.Mahuahua.dto;

import com.example.Mahuahua.model.EstadoCita;
import java.time.LocalDateTime;

public record CitaResponseDTO(
    Long id,
    LocalDateTime fechaHora,
    String motivo,
    EstadoCita estado,
    PacienteResponseDTO paciente,
    PsicologoResponseDTO psicologo
) {}
