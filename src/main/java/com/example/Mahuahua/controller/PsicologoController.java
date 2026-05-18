package com.example.Mahuahua.controller;

import com.example.Mahuahua.dto.PsicologoRequestDTO;
import com.example.Mahuahua.dto.PsicologoResponseDTO;
import com.example.Mahuahua.service.PsicologoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psicologos")
@RequiredArgsConstructor
public class PsicologoController {

    private final PsicologoService psicologoService;

    @PostMapping
    public ResponseEntity<PsicologoResponseDTO> createPsicologo(@Valid @RequestBody PsicologoRequestDTO request) {
        return new ResponseEntity<>(psicologoService.createPsicologo(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PsicologoResponseDTO> getPsicologoById(@PathVariable Long id) {
        return ResponseEntity.ok(psicologoService.getPsicologoById(id));
    }

    @GetMapping
    public ResponseEntity<List<PsicologoResponseDTO>> getAllPsicologos() {
        return ResponseEntity.ok(psicologoService.getAllPsicologos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PsicologoResponseDTO> updatePsicologo(@PathVariable Long id, @Valid @RequestBody PsicologoRequestDTO request) {
        return ResponseEntity.ok(psicologoService.updatePsicologo(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePsicologo(@PathVariable Long id) {
        psicologoService.deletePsicologo(id);
        return ResponseEntity.noContent().build();
    }
}
