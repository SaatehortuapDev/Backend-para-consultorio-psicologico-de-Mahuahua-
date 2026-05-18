package com.example.Mahuahua.controller;

import com.example.Mahuahua.dto.CitaRequestDTO;
import com.example.Mahuahua.dto.CitaResponseDTO;
import com.example.Mahuahua.model.EstadoCita;
import com.example.Mahuahua.service.CitaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @PostMapping
    public ResponseEntity<CitaResponseDTO> createCita(@Valid @RequestBody CitaRequestDTO request) {
        return new ResponseEntity<>(citaService.createCita(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> getCitaById(@PathVariable Long id) {
        return ResponseEntity.ok(citaService.getCitaById(id));
    }

    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<CitaResponseDTO>> getCitasByPaciente(@PathVariable Long pacienteId) {
        return ResponseEntity.ok(citaService.getCitasByPaciente(pacienteId));
    }

    @GetMapping("/psicologo/{psicologoId}")
    public ResponseEntity<List<CitaResponseDTO>> getCitasByPsicologo(@PathVariable Long psicologoId) {
        return ResponseEntity.ok(citaService.getCitasByPsicologo(psicologoId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<CitaResponseDTO> updateEstadoCita(@PathVariable Long id, @RequestParam EstadoCita estado) {
        return ResponseEntity.ok(citaService.updateEstadoCita(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}
