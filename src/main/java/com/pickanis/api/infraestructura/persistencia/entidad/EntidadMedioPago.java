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
@Table(name = "medios_pago")
public class EntidadMedioPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedio;

    @OneToOne
    @JoinColumn(name = "id_pservicio", referencedColumnName = "idServicio", nullable = false)
    private EntidadServicio  codigoServicio;

    @NotBlank(message = "El campo Descripción no puede estar vacío")
    @Column(length = 250, nullable = false)
    private String descripcion;

    @Column(length = 250)
    private String observaciones;

    @NotBlank(message = "El campo Estado Publicador no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estadoPublicador;

    @NotBlank(message = "El campo Estado Presentador no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estadoPrestador;
}
