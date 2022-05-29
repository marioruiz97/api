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
@Table(name = "calificaciones", uniqueConstraints = {@UniqueConstraint(name = "uk_calificacion", columnNames = {"idCalificaciones"})})
public class EntidadCalificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCalificaciones;

    @ManyToOne(optional = false)
    @JoinColumn(name = "identificacion_Calificado", referencedColumnName = "identificacion")
    private EntidadUsuario usuarioCalificado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "identificacion_Calificador", referencedColumnName = "identificacion")
    private EntidadUsuario usuarioCalificador;

    @NotBlank(message = "El campo Evaluación no puede estar vacío")
    @Column(length = 2, nullable = false)
    private Integer evaluacion;

    @Column(length = 250)
    private String comentarios;
}
