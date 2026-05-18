package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.CitaRequestDTO;
import com.example.Mahuahua.dto.CitaResponseDTO;
import com.example.Mahuahua.model.EstadoCita;

import java.util.List;

public interface CitaService {
    CitaResponseDTO createCita(CitaRequestDTO request);
    CitaResponseDTO getCitaById(Long id);
    List<CitaResponseDTO> getCitasByPaciente(Long pacienteId);
    List<CitaResponseDTO> getCitasByPsicologo(Long psicologoId);
    CitaResponseDTO updateEstadoCita(Long id, EstadoCita estado);
    void deleteCita(Long id);
}
