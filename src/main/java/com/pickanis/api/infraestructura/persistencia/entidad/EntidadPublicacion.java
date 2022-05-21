package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publicaciones")
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

    private LocalDate fechaPublicacion;

    private LocalDateTime fechaInicio;

    private LocalDateTime fechaFinal;

    private String estado;

    private String tipo;

    private String descripcion;


}
