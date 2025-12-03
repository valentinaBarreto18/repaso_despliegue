package com.veterinaria.mascotas.controller;

import com.veterinaria.mascotas.model.Mascota;
import com.veterinaria.mascotas.service.MascotaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {
    
    @Autowired
    private MascotaService mascotaService;
    
    @GetMapping
    public ResponseEntity<List<Mascota>> obtenerTodasLasMascotas() {
        return ResponseEntity.ok(mascotaService.obtenerTodasLasMascotas());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Long id) {
        return mascotaService.obtenerMascotaPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/dueno/{nombreDueno}")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorDueno(@PathVariable String nombreDueno) {
        return ResponseEntity.ok(mascotaService.obtenerMascotasPorDueno(nombreDueno));
    }
    
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Mascota>> obtenerMascotasPorEspecie(@PathVariable String especie) {
        return ResponseEntity.ok(mascotaService.obtenerMascotasPorEspecie(especie));
    }
    
    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@Valid @RequestBody Mascota mascota) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(mascotaService.crearMascota(mascota));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(
            @PathVariable Long id,
            @Valid @RequestBody Mascota mascota) {
        try {
            return ResponseEntity.ok(mascotaService.actualizarMascota(id, mascota));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();
    }
}
