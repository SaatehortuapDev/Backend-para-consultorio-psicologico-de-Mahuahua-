package com.example.Mahuahua.repository;

import com.example.Mahuahua.model.Evolucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvolucionRepository extends JpaRepository<Evolucion, Long> {
    List<Evolucion> findByHistoriaClinicaId(Long historiaClinicaId);
    List<Evolucion> findByCitaId(Long citaId);
}
