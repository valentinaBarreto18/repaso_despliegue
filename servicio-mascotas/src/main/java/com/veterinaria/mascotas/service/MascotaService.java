package com.veterinaria.mascotas.service;

import com.veterinaria.mascotas.model.Mascota;
import com.veterinaria.mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    public List<Mascota> obtenerTodasLasMascotas() {
        return mascotaRepository.findAll();
    }
    
    public Optional<Mascota> obtenerMascotaPorId(Long id) {
        return mascotaRepository.findById(id);
    }
    
    public List<Mascota> obtenerMascotasPorDueno(String nombreDueno) {
        return mascotaRepository.findByNombreDueno(nombreDueno);
    }
    
    public List<Mascota> obtenerMascotasPorEspecie(String especie) {
        return mascotaRepository.findByEspecie(especie);
    }
    
    public Mascota crearMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
    
    public Mascota actualizarMascota(Long id, Mascota mascotaActualizada) {
        return mascotaRepository.findById(id)
            .map(mascota -> {
                mascota.setNombre(mascotaActualizada.getNombre());
                mascota.setEspecie(mascotaActualizada.getEspecie());
                mascota.setRaza(mascotaActualizada.getRaza());
                mascota.setFechaNacimiento(mascotaActualizada.getFechaNacimiento());
                mascota.setNombreDueno(mascotaActualizada.getNombreDueno());
                mascota.setTelefono(mascotaActualizada.getTelefono());
                mascota.setEmail(mascotaActualizada.getEmail());
                mascota.setObservaciones(mascotaActualizada.getObservaciones());
                return mascotaRepository.save(mascota);
            })
            .orElseThrow(() -> new RuntimeException("Mascota no encontrada con id: " + id));
    }
    
    public void eliminarMascota(Long id) {
        mascotaRepository.deleteById(id);
    }
}
