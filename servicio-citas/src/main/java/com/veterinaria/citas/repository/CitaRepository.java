package com.veterinaria.citas.repository;

import com.veterinaria.citas.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByMascotaId(Long mascotaId);
    List<Cita> findByVeterinario(String veterinario);
    List<Cita> findByEstado(Cita.EstadoCita estado);
    List<Cita> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
