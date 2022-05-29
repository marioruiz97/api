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
@Table(name = "servicios", uniqueConstraints = {@UniqueConstraint(name = "uk_servicio", columnNames = {"idServicio" })})
public class EntidadServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    @OneToOne
    @JoinColumn(name = "id_publicacion", referencedColumnName = "idPublicacion", nullable = false)
    private EntidadPublicacion  codigoPublicacion;

    @OneToOne
    @JoinColumn(name = "identificacion_paseador", referencedColumnName = "identificacion", nullable = false)
    private EntidadUsuario identificacionPaseador;

    @NotBlank(message = "El campo Estado no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estado;

    @NotBlank(message = "El campo Observaciones no puede estar vacío")
    @Column(length = 250, nullable = false)
    private String observaciones;
}
