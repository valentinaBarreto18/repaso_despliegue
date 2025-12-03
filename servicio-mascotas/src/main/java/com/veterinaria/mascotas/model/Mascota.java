package com.veterinaria.mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "mascotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;
    
    @NotBlank(message = "La especie es obligatoria")
    @Column(nullable = false)
    private String especie;
    
    private String raza;
    
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    
    @NotBlank(message = "El nombre del dueño es obligatorio")
    @Column(name = "nombre_dueno", nullable = false)
    private String nombreDueno;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Column(nullable = false)
    private String telefono;
    
    private String email;
    
    @Column(columnDefinition = "TEXT")
    private String observaciones;
}
