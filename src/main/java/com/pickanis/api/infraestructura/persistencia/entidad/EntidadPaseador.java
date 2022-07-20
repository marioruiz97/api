package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "paseadores", uniqueConstraints = {@UniqueConstraint(name = "uk_paseador", columnNames = {"idPaseador"})})
public class EntidadPaseador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaseador;

    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @OneToOne(cascade = CascadeType.ALL)
    private EntidadUsuario usuario;

    @NotBlank(message = "El campo Tiempo de Experiencia no puede estar vacío")
    @Column(length = 100, nullable = false)
    private String tiempoExperiencia;

    @NotBlank(message = "El campo Perfil Experiencia no puede estar vacío")
    @Column(length = 250, nullable = false)
    private String perfilExperiencia;

    @NotNull(message = "El campo Estado no puede estar vacío")
    @Column(nullable = false)
    private Boolean estado;

    @Column(length = 2)
    private Integer calificacion;
}
