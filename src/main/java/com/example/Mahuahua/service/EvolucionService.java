package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.EvolucionRequestDTO;
import com.example.Mahuahua.dto.EvolucionResponseDTO;

import java.util.List;

public interface EvolucionService {
    EvolucionResponseDTO createEvolucion(EvolucionRequestDTO request);
    List<EvolucionResponseDTO> getEvolucionesByHistoriaClinica(Long historiaClinicaId);
    void deleteEvolucion(Long id);
}
