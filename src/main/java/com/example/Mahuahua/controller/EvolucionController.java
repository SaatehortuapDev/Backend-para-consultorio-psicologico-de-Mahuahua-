package com.example.Mahuahua.controller;

import com.example.Mahuahua.dto.EvolucionRequestDTO;
import com.example.Mahuahua.dto.EvolucionResponseDTO;
import com.example.Mahuahua.service.EvolucionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evoluciones")
@RequiredArgsConstructor
public class EvolucionController {

    private final EvolucionService evolucionService;

    @PostMapping
    public ResponseEntity<EvolucionResponseDTO> createEvolucion(@Valid @RequestBody EvolucionRequestDTO request) {
        return new ResponseEntity<>(evolucionService.createEvolucion(request), HttpStatus.CREATED);
    }

    @GetMapping("/historia-clinica/{historiaClinicaId}")
    public ResponseEntity<List<EvolucionResponseDTO>> getEvolucionesByHistoriaClinica(@PathVariable Long historiaClinicaId) {
        return ResponseEntity.ok(evolucionService.getEvolucionesByHistoriaClinica(historiaClinicaId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvolucion(@PathVariable Long id) {
        evolucionService.deleteEvolucion(id);
        return ResponseEntity.noContent().build();
    }
}
