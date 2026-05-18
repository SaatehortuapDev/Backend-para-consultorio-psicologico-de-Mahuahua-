package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PagoRequestDTO;
import com.example.Mahuahua.dto.PagoResponseDTO;
import com.example.Mahuahua.model.EstadoPago;

public interface PagoService {
    PagoResponseDTO createPago(PagoRequestDTO request);
    PagoResponseDTO getPagoByCita(Long citaId);
    PagoResponseDTO updateEstadoPago(Long id, EstadoPago estado);
    void deletePago(Long id);
}
