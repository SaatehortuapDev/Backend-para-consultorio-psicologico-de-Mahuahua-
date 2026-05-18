package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PacienteRequestDTO;
import com.example.Mahuahua.dto.PacienteResponseDTO;
import com.example.Mahuahua.exception.BadRequestException;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.HistoriaClinica;
import com.example.Mahuahua.model.Paciente;
import com.example.Mahuahua.repository.HistoriaClinicaRepository;
import com.example.Mahuahua.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    @Override
    @Transactional
    public PacienteResponseDTO createPaciente(PacienteRequestDTO request) {
        if (pacienteRepository.findByEmail(request.email()).isPresent()) {
            throw new BadRequestException("El email ya está registrado");
        }

        Paciente paciente = Paciente.builder()
                .nombre(request.nombre())
                .apellidos(request.apellidos())
                .email(request.email())
                .telefono(request.telefono())
                .fechaNacimiento(request.fechaNacimiento())
                .build();

        Paciente savedPaciente = pacienteRepository.save(paciente);

        // Crear historia clinica automaticamente
        HistoriaClinica historiaClinica = HistoriaClinica.builder()
                .fechaCreacion(LocalDateTime.now())
                .paciente(savedPaciente)
                .build();
        historiaClinicaRepository.save(historiaClinica);

        return mapToResponseDTO(savedPaciente);
    }

    @Override
    public PacienteResponseDTO getPacienteById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        return mapToResponseDTO(paciente);
    }

    @Override
    public List<PacienteResponseDTO> getAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PacienteResponseDTO updatePaciente(Long id, PacienteRequestDTO request) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));

        if (!paciente.getEmail().equals(request.email()) && pacienteRepository.findByEmail(request.email()).isPresent()) {
            throw new BadRequestException("El email ya está registrado por otro paciente");
        }

        paciente.setNombre(request.nombre());
        paciente.setApellidos(request.apellidos());
        paciente.setEmail(request.email());
        paciente.setTelefono(request.telefono());
        paciente.setFechaNacimiento(request.fechaNacimiento());

        return mapToResponseDTO(pacienteRepository.save(paciente));
    }

    @Override
    @Transactional
    public void deletePaciente(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        pacienteRepository.delete(paciente);
    }

    private PacienteResponseDTO mapToResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getApellidos(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getFechaNacimiento()
        );
    }
}
