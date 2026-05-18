package com.example.Mahuahua.controller;

import com.example.Mahuahua.dto.HistoriaClinicaResponseDTO;
import com.example.Mahuahua.service.HistoriaClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/historias-clinicas")
@RequiredArgsConstructor
public class HistoriaClinicaController {

    private final HistoriaClinicaService historiaClinicaService;

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<HistoriaClinicaResponseDTO> getHistoriaClinicaByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(historiaClinicaService.getHistoriaClinicaByPacienteId(pacienteId));
    }
}
