package com.example.Mahuahua.repository;

import com.example.Mahuahua.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByPacienteId(Long pacienteId);
    List<Cita> findByPsicologoId(Long psicologoId);
    boolean existsByPsicologoIdAndFechaHora(Long psicologoId, LocalDateTime fechaHora);
}
