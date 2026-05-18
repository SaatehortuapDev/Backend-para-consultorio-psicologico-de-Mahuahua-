package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.EvolucionResponseDTO;
import com.example.Mahuahua.dto.HistoriaClinicaResponseDTO;
import com.example.Mahuahua.dto.PacienteResponseDTO;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.HistoriaClinica;
import com.example.Mahuahua.repository.HistoriaClinicaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    public HistoriaClinicaResponseDTO getHistoriaClinicaByPacienteId(Long pacienteId) {
        HistoriaClinica historiaClinica = historiaClinicaRepository.findByPacienteId(pacienteId)
                .orElseThrow(() -> new ResourceNotFoundException("Historia Clínica no encontrada para el paciente"));

        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO(
                historiaClinica.getPaciente().getId(),
                historiaClinica.getPaciente().getNombre(),
                historiaClinica.getPaciente().getApellidos(),
                historiaClinica.getPaciente().getEmail(),
                historiaClinica.getPaciente().getTelefono(),
                historiaClinica.getPaciente().getFechaNacimiento()
        );

        List<EvolucionResponseDTO> evoluciones = historiaClinica.getEvoluciones().stream()
                .map(e -> new EvolucionResponseDTO(
                        e.getId(),
                        e.getFecha(),
                        e.getNotas(),
                        e.getDiagnostico(),
                        e.getCita() != null ? e.getCita().getId() : null
                )).collect(Collectors.toList());

        return new HistoriaClinicaResponseDTO(
                historiaClinica.getId(),
                historiaClinica.getFechaCreacion(),
                pacienteDTO,
                evoluciones
        );
    }
}
