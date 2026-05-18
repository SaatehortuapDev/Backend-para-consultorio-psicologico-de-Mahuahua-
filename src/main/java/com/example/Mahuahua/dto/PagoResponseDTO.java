package com.example.Mahuahua.dto;

import com.example.Mahuahua.model.EstadoPago;
import com.example.Mahuahua.model.MetodoPago;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PagoResponseDTO(
    Long id,
    BigDecimal monto,
    LocalDateTime fechaPago,
    MetodoPago metodoPago,
    EstadoPago estado,
    Long citaId
) {}
