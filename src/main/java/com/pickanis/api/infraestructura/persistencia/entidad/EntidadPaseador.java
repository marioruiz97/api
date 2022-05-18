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
@Table(name = "paseadores")
public class EntidadPaseador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @OneToOne(cascade = CascadeType.ALL)
    private EntidadUsuario usuario;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 3, nullable = false)
    private Integer tiempoExperiencia;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 250, nullable = false)
    private String perfilExperiencia;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estado;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 2, nullable = false)
    private Integer calificacion;
}
