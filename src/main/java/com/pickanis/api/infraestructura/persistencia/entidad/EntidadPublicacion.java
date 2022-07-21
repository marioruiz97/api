package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(name = "uk_publicacion", columnNames = {"idPublicacion"})})
public class EntidadPublicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @ManyToOne
    @JoinColumn(name = "identificacion_publicador", referencedColumnName = "identificacion", nullable = false)
    private EntidadUsuario publicador;

    @ManyToOne
    @JoinColumn(name = "id_mascota", referencedColumnName = "idMascota", nullable = false)
    private EntidadMascota mascota;

    @NotBlank(message = "El campo Fecha Publicación no puede estar vacío")
    @Column( nullable = false)
    private LocalDate fechaPublicacion;

    @NotBlank(message = "El campo Fecha Inicio no puede estar vacío")
    @Column(nullable = false)
    private LocalDateTime fechaInicio;

    @NotBlank(message = "El campo Fehca Final no puede estar vacío")
    @Column(nullable = false)
    private LocalDateTime fechaFinal;

    @NotNull(message = "El campo Estado no puede estar vacío")
    @Column(nullable = false)
    private Boolean estado;

    @NotBlank(message = "El campo Tipo no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String tipo;

    @NotBlank(message = "El campo Descripción no puede estar vacío")
    @Column(length = 250)
    private String descripcion;

    @Column(length = 100) //Dirreccion a la ubicacionde la imagen
    private String foto;
}
