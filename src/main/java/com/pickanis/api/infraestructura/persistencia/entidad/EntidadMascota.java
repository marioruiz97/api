package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "mascotas")
public class EntidadMascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMascota;

    private String nombre;

    private String raza;

    private Integer edad;

    @ManyToOne
    @JoinColumn(name = "identificacion_dueno", referencedColumnName = "identificacion", nullable = false)
    private EntidadUsuario dueno;

    private Integer peso;

    private String observaciones;


}
