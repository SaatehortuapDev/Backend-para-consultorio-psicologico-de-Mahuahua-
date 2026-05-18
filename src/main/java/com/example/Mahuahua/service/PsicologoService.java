package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.PsicologoRequestDTO;
import com.example.Mahuahua.dto.PsicologoResponseDTO;

import java.util.List;

public interface PsicologoService {
    PsicologoResponseDTO createPsicologo(PsicologoRequestDTO request);
    PsicologoResponseDTO getPsicologoById(Long id);
    List<PsicologoResponseDTO> getAllPsicologos();
    PsicologoResponseDTO updatePsicologo(Long id, PsicologoRequestDTO request);
    void deletePsicologo(Long id);
}
