package com.example.Mahuahua.dto;

import com.example.Mahuahua.model.MetodoPago;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record PagoRequestDTO(
    @NotNull(message = "El id de la cita es obligatorio") Long citaId,
    @NotNull(message = "El monto es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El monto debe ser mayor a 0") BigDecimal monto,
    @NotNull(message = "El método de pago es obligatorio") MetodoPago metodoPago
) {}
