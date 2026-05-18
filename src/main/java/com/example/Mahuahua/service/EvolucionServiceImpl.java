package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.EvolucionRequestDTO;
import com.example.Mahuahua.dto.EvolucionResponseDTO;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.Cita;
import com.example.Mahuahua.model.Evolucion;
import com.example.Mahuahua.model.HistoriaClinica;
import com.example.Mahuahua.repository.CitaRepository;
import com.example.Mahuahua.repository.EvolucionRepository;
import com.example.Mahuahua.repository.HistoriaClinicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EvolucionServiceImpl implements EvolucionService {

    private final EvolucionRepository evolucionRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final CitaRepository citaRepository;

    @Override
    @Transactional
    public EvolucionResponseDTO createEvolucion(EvolucionRequestDTO request) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(request.historiaClinicaId())
                .orElseThrow(() -> new ResourceNotFoundException("Historia Clínica no encontrada"));

        Cita cita = null;
        if (request.citaId() != null) {
            cita = citaRepository.findById(request.citaId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
        }

        Evolucion evolucion = Evolucion.builder()
                .fecha(LocalDateTime.now())
                .notas(request.notas())
                .diagnostico(request.diagnostico())
                .historiaClinica(historiaClinica)
                .cita(cita)
                .build();

        return mapToResponseDTO(evolucionRepository.save(evolucion));
    }

    @Override
    public List<EvolucionResponseDTO> getEvolucionesByHistoriaClinica(Long historiaClinicaId) {
        return evolucionRepository.findByHistoriaClinicaId(historiaClinicaId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteEvolucion(Long id) {
        Evolucion evolucion = evolucionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evolución no encontrada"));
        evolucionRepository.delete(evolucion);
    }

    private EvolucionResponseDTO mapToResponseDTO(Evolucion evolucion) {
        return new EvolucionResponseDTO(
                evolucion.getId(),
                evolucion.getFecha(),
                evolucion.getNotas(),
                evolucion.getDiagnostico(),
                evolucion.getCita() != null ? evolucion.getCita().getId() : null
        );
    }
}
