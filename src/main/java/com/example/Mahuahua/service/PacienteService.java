package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PacienteRequestDTO;
import com.example.Mahuahua.dto.PacienteResponseDTO;

import java.util.List;

public interface PacienteService {
    PacienteResponseDTO createPaciente(PacienteRequestDTO request);
    PacienteResponseDTO getPacienteById(Long id);
    List<PacienteResponseDTO> getAllPacientes();
    PacienteResponseDTO updatePaciente(Long id, PacienteRequestDTO request);
    void deletePaciente(Long id);
}
