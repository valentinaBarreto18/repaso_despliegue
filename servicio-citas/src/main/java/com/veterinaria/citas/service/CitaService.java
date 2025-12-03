package com.veterinaria.citas.service;

import com.veterinaria.citas.model.Cita;
import com.veterinaria.citas.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    
    @Autowired
    private CitaRepository citaRepository;
    
    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }
    
    public Optional<Cita> obtenerCitaPorId(Long id) {
        return citaRepository.findById(id);
    }
    
    public List<Cita> obtenerCitasPorMascota(Long mascotaId) {
        return citaRepository.findByMascotaId(mascotaId);
    }
    
    public List<Cita> obtenerCitasPorVeterinario(String veterinario) {
        return citaRepository.findByVeterinario(veterinario);
    }
    
    public List<Cita> obtenerCitasPorEstado(Cita.EstadoCita estado) {
        return citaRepository.findByEstado(estado);
    }
    
    public List<Cita> obtenerCitasPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return citaRepository.findByFechaHoraBetween(inicio, fin);
    }
    
    public Cita crearCita(Cita cita) {
        return citaRepository.save(cita);
    }
    
    public Cita actualizarCita(Long id, Cita citaActualizada) {
        return citaRepository.findById(id)
            .map(cita -> {
                cita.setMascotaId(citaActualizada.getMascotaId());
                cita.setNombreMascota(citaActualizada.getNombreMascota());
                cita.setFechaHora(citaActualizada.getFechaHora());
                cita.setMotivo(citaActualizada.getMotivo());
                cita.setVeterinario(citaActualizada.getVeterinario());
                cita.setEstado(citaActualizada.getEstado());
                cita.setObservaciones(citaActualizada.getObservaciones());
                cita.setDiagnostico(citaActualizada.getDiagnostico());
                cita.setTratamiento(citaActualizada.getTratamiento());
                return citaRepository.save(cita);
            })
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }
    
    public Cita actualizarEstadoCita(Long id, Cita.EstadoCita nuevoEstado) {
        return citaRepository.findById(id)
            .map(cita -> {
                cita.setEstado(nuevoEstado);
                return citaRepository.save(cita);
            })
            .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }
    
    public void eliminarCita(Long id) {
        citaRepository.deleteById(id);
    }
}
