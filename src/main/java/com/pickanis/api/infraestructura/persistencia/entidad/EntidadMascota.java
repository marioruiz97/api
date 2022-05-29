package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mascotas", uniqueConstraints = {@UniqueConstraint(name = "uk_mascota", columnNames = {"idMascota","nombre"})})
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

    @NotBlank(message = "El campo Edad no puede estar vacío")
    @Column(length = 2, nullable = false)
    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "identificacion_dueno", referencedColumnName = "identificacion", nullable = false)
    private EntidadUsuario dueno;

    @Column(length = 3)
    private Integer peso;

    @Column(length = 250)
    private String observaciones;

    @Column(length = 100) //Dirreccion a la ubicacionde la imagen
    private String foto;
}
