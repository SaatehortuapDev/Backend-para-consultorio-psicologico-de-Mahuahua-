package com.example.Mahuahua.dto;

import java.time.LocalDateTime;

public record EvolucionResponseDTO(
    Long id,
    LocalDateTime fecha,
    String notas,
    String diagnostico,
    Long citaId
) {}
