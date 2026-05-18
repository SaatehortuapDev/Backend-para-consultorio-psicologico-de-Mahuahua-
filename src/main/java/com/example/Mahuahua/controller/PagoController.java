package com.example.Mahuahua.controller;

import com.example.Mahuahua.dto.PagoRequestDTO;
import com.example.Mahuahua.dto.PagoResponseDTO;
import com.example.Mahuahua.model.EstadoPago;
import com.example.Mahuahua.service.PagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    @PostMapping
    public ResponseEntity<PagoResponseDTO> createPago(@Valid @RequestBody PagoRequestDTO request) {
        return new ResponseEntity<>(pagoService.createPago(request), HttpStatus.CREATED);
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<PagoResponseDTO> getPagoByCita(@PathVariable Long citaId) {
        return ResponseEntity.ok(pagoService.getPagoByCita(citaId));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PagoResponseDTO> updateEstadoPago(@PathVariable Long id, @RequestParam EstadoPago estado) {
        return ResponseEntity.ok(pagoService.updateEstadoPago(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
