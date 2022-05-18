package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rutas")
public class EntidadRuta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    private Integer identificadorPaseador;

    private LocalDate fechaRuta;

    private LocalTime horaSalida;

    private String puntoSalida;

    private String puntoLlegada;

    private String descripcionRecorrido;

    private String estado;


}
