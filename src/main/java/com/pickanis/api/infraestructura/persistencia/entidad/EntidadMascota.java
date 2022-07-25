package com.pickanis.api.infraestructura.persistencia.entidad;

import com.pickanis.api.dominio.modelo.SexoMascota;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mascotas", uniqueConstraints = {@UniqueConstraint(name = "uk_mascota", columnNames = {"idMascota", "nombre"})})
public class EntidadMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    @NotBlank(message = "El campo Nombre no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombre;

    @NotBlank(message = "El campo Raza no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String raza;

    @NotNull
    @Column(nullable = false)
    private SexoMascota sexo;

    @NotNull(message = "El campo fecha nacimiento no puede estar vacío")
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "identificacion_dueno", referencedColumnName = "identificacion", nullable = false)
    private EntidadUsuario dueno;

    @NotNull
    @Column(length = 3)
    private Integer peso;

    @Column(length = 250)
    private String observaciones;

    @Column //Direccion url a la ubicacion de la imagen
    private String foto;
}
