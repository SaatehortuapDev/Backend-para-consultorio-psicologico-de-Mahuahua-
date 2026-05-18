package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PagoRequestDTO;
import com.example.Mahuahua.dto.PagoResponseDTO;
import com.example.Mahuahua.exception.BadRequestException;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.Cita;
import com.example.Mahuahua.model.EstadoPago;
import com.example.Mahuahua.model.Pago;
import com.example.Mahuahua.repository.CitaRepository;
import com.example.Mahuahua.repository.PagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagoServiceImpl implements PagoService {

    private final PagoRepository pagoRepository;
    private final CitaRepository citaRepository;

    @Override
    @Transactional
    public PagoResponseDTO createPago(PagoRequestDTO request) {
        Cita cita = citaRepository.findById(request.citaId())
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));

        if (pagoRepository.findByCitaId(cita.getId()).isPresent()) {
            throw new BadRequestException("Ya existe un pago registrado para esta cita");
        }

        Pago pago = Pago.builder()
                .monto(request.monto())
                .fechaPago(LocalDateTime.now())
                .metodoPago(request.metodoPago())
                .estado(EstadoPago.COMPLETADO) // Assume it's completed upon creation, or PENDIENTE if requested
                .cita(cita)
                .build();

        return mapToResponseDTO(pagoRepository.save(pago));
    }

    @Override
    public PagoResponseDTO getPagoByCita(Long citaId) {
        Pago pago = pagoRepository.findByCitaId(citaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado para la cita"));
        return mapToResponseDTO(pago);
    }

    @Override
    @Transactional
    public PagoResponseDTO updateEstadoPago(Long id, EstadoPago estado) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        pago.setEstado(estado);
        return mapToResponseDTO(pagoRepository.save(pago));
    }

    @Override
    @Transactional
    public void deletePago(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado"));
        pagoRepository.delete(pago);
    }

    private PagoResponseDTO mapToResponseDTO(Pago pago) {
        return new PagoResponseDTO(
                pago.getId(),
                pago.getMonto(),
                pago.getFechaPago(),
                pago.getMetodoPago(),
                pago.getEstado(),
                pago.getCita().getId()
        );
    }
}
