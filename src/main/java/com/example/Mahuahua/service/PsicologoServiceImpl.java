package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PsicologoRequestDTO;
import com.example.Mahuahua.dto.PsicologoResponseDTO;
import com.example.Mahuahua.exception.BadRequestException;
import com.example.Mahuahua.exception.ResourceNotFoundException;
import com.example.Mahuahua.model.Psicologo;
import com.example.Mahuahua.repository.PsicologoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PsicologoServiceImpl implements PsicologoService {

    private final PsicologoRepository psicologoRepository;

    @Override
    @Transactional
    public PsicologoResponseDTO createPsicologo(PsicologoRequestDTO request) {
        if (psicologoRepository.findByEmail(request.email()).isPresent()) {
            throw new BadRequestException("El email ya está registrado");
        }

        Psicologo psicologo = Psicologo.builder()
                .nombre(request.nombre())
                .apellidos(request.apellidos())
                .especialidad(request.especialidad())
                .email(request.email())
                .build();

        return mapToResponseDTO(psicologoRepository.save(psicologo));
    }

    @Override
    public PsicologoResponseDTO getPsicologoById(Long id) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));
        return mapToResponseDTO(psicologo);
    }

    @Override
    public List<PsicologoResponseDTO> getAllPsicologos() {
        return psicologoRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PsicologoResponseDTO updatePsicologo(Long id, PsicologoRequestDTO request) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));

        if (!psicologo.getEmail().equals(request.email()) && psicologoRepository.findByEmail(request.email()).isPresent()) {
            throw new BadRequestException("El email ya está registrado por otro psicólogo");
        }

        psicologo.setNombre(request.nombre());
        psicologo.setApellidos(request.apellidos());
        psicologo.setEspecialidad(request.especialidad());
        psicologo.setEmail(request.email());

        return mapToResponseDTO(psicologoRepository.save(psicologo));
    }

    @Override
    @Transactional
    public void deletePsicologo(Long id) {
        Psicologo psicologo = psicologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Psicólogo no encontrado"));
        psicologoRepository.delete(psicologo);
    }

    private PsicologoResponseDTO mapToResponseDTO(Psicologo psicologo) {
        return new PsicologoResponseDTO(
                psicologo.getId(),
                psicologo.getNombre(),
                psicologo.getApellidos(),
                psicologo.getEspecialidad(),
                psicologo.getEmail()
        );
    }
}
