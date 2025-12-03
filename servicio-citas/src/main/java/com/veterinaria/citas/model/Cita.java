package com.veterinaria.citas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID de la mascota es obligatorio")
    @Column(name = "mascota_id", nullable = false)
    private Long mascotaId;
    
    @NotBlank(message = "El nombre de la mascota es obligatorio")
    @Column(name = "nombre_mascota", nullable = false)
    private String nombreMascota;
    
    @NotNull(message = "La fecha y hora son obligatorias")
    @Column(name = "fecha_hora", nullable = false)
    private LocalDateTime fechaHora;
    
    @NotBlank(message = "El motivo es obligatorio")
    @Column(nullable = false)
    private String motivo;
    
    @NotBlank(message = "El veterinario es obligatorio")
    @Column(nullable = false)
    private String veterinario;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado = EstadoCita.PENDIENTE;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(columnDefinition = "TEXT")
    private String diagnostico;
    
    @Column(columnDefinition = "TEXT")
    private String tratamiento;
    
    public enum EstadoCita {
        PENDIENTE,
        CONFIRMADA,
        EN_CONSULTA,
        COMPLETADA,
        CANCELADA
    }
}
