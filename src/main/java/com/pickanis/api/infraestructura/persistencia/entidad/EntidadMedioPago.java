package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medios_pago")
public class EntidadMedioPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedio;

    private Integer codigoServicio;

    private String descripcion;

    private String observaciones;

    private String estadoPublicador;

    private String estadoPrestador;
}
