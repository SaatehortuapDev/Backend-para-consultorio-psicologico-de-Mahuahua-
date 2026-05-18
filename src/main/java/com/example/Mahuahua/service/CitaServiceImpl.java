package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.CitaRequestDTO;
import com.example.Mahuahua.dto.CitaResponseDTO;
import com.example.Mahuahua.dto.PacienteResponseDTO;
import com.example.Mahuahua.dto.PsicologoResponseDTO;
import com.example.Mahuahua.exception.BadRequestException;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.Cita;
import com.example.Mahuahua.model.EstadoCita;
import com.example.Mahuahua.model.Paciente;
import com.example.Mahuahua.model.Psicologo;
import com.example.Mahuahua.repository.CitaRepository;
import com.example.Mahuahua.repository.PacienteRepository;
import com.example.Mahuahua.repository.PsicologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    @Override
    @Transactional
    public CitaResponseDTO createCita(CitaRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(request.pacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        Psicologo psicologo = psicologoRepository.findById(request.psicologoId())
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));

        if (citaRepository.existsByPsicologoIdAndFechaHora(psicologo.getId(), request.fechaHora())) {
            throw new BadRequestException("El psicólogo ya tiene una cita asignada en ese horario");
        }

        Cita cita = Cita.builder()
                .paciente(paciente)
                .psicologo(psicologo)
                .fechaHora(request.fechaHora())
                .motivo(request.motivo())
                .estado(EstadoCita.PENDIENTE)
                .build();

        return mapToResponseDTO(citaRepository.save(cita));
    }

    @Override
    public CitaResponseDTO getCitaById(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
        return mapToResponseDTO(cita);
    }

    @Override
    public List<CitaResponseDTO> getCitasByPaciente(Long pacienteId) {
        return citaRepository.findByPacienteId(pacienteId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CitaResponseDTO> getCitasByPsicologo(Long psicologoId) {
        return citaRepository.findByPsicologoId(psicologoId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CitaResponseDTO updateEstadoCita(Long id, EstadoCita estado) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
        cita.setEstado(estado);
        return mapToResponseDTO(citaRepository.save(cita));
    }

    @Override
    @Transactional
    public void deleteCita(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada"));
        citaRepository.delete(cita);
    }

    private CitaResponseDTO mapToResponseDTO(Cita cita) {
        PacienteResponseDTO pacienteDTO = new PacienteResponseDTO(
                cita.getPaciente().getId(),
                cita.getPaciente().getNombre(),
                cita.getPaciente().getApellidos(),
                cita.getPaciente().getEmail(),
                cita.getPaciente().getTelefono(),
                cita.getPaciente().getFechaNacimiento()
        );

        PsicologoResponseDTO psicologoDTO = new PsicologoResponseDTO(
                cita.getPsicologo().getId(),
                cita.getPsicologo().getNombre(),
                cita.getPsicologo().getApellidos(),
                cita.getPsicologo().getEspecialidad(),
                cita.getPsicologo().getEmail()
        );

        return new CitaResponseDTO(
                cita.getId(),
                cita.getFechaHora(),
                cita.getMotivo(),
                cita.getEstado(),
                pacienteDTO,
                psicologoDTO
        );
    }
}
