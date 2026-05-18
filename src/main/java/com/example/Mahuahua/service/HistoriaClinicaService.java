package com.example.Mahuahua.service;

import com.example.Mahuahua.dto.HistoriaClinicaResponseDTO;

public interface HistoriaClinicaService {
    HistoriaClinicaResponseDTO getHistoriaClinicaByPacienteId(Long pacienteId);
}
