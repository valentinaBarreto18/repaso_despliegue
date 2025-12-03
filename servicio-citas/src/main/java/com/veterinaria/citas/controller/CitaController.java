package com.veterinaria.citas.controller;

import com.veterinaria.citas.model.Cita;
import com.veterinaria.citas.service.CitaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/citas")
@CrossOrigin(origins = "*")
public class CitaController {
    
    @Autowired
    private CitaService citaService;
    
    @GetMapping
    public ResponseEntity<List<Cita>> obtenerTodasLasCitas() {
        return ResponseEntity.ok(citaService.obtenerTodasLasCitas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cita> obtenerCitaPorId(@PathVariable Long id) {
        return citaService.obtenerCitaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/mascota/{mascotaId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorMascota(@PathVariable Long mascotaId) {
        return ResponseEntity.ok(citaService.obtenerCitasPorMascota(mascotaId));
    }
    
    @GetMapping("/veterinario/{veterinario}")
    public ResponseEntity<List<Cita>> obtenerCitasPorVeterinario(@PathVariable String veterinario) {
        return ResponseEntity.ok(citaService.obtenerCitasPorVeterinario(veterinario));
    }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Cita>> obtenerCitasPorEstado(@PathVariable Cita.EstadoCita estado) {
        return ResponseEntity.ok(citaService.obtenerCitasPorEstado(estado));
    }
    
    @GetMapping("/fechas")
    public ResponseEntity<List<Cita>> obtenerCitasPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return ResponseEntity.ok(citaService.obtenerCitasPorFechas(inicio, fin));
    }
    
    @PostMapping
    public ResponseEntity<Cita> crearCita(@Valid @RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(citaService.crearCita(cita));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizarCita(
            @PathVariable Long id,
            @Valid @RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.actualizarCita(id, cita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Cita> actualizarEstadoCita(
            @PathVariable Long id,
            @RequestParam Cita.EstadoCita estado) {
        try {
            return ResponseEntity.ok(citaService.actualizarEstadoCita(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCita(@PathVariable Long id) {
        citaService.eliminarCita(id);
        return ResponseEntity.noContent().build();
    }
}
