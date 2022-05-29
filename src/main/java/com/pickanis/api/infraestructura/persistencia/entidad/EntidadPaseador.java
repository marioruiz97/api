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

@Table(name = "paseadores", uniqueConstraints = {@UniqueConstraint(name = "uk_paseador", columnNames = {"idPaseador"})})
public class EntidadPaseador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPaseador;

    @JoinColumn(name = "identificacion", referencedColumnName = "identificacion")
    @OneToOne(cascade = CascadeType.ALL)
    private EntidadUsuario usuario;

    @NotBlank(message = "El campo Tiempo de Experiencia no puede estar vacío")
    @Column(length = 3, nullable = false)
    private Integer tiempoExperiencia;

    @NotBlank(message = "El campo Perfil Experiencia no puede estar vacío")
    @Column(length = 250, nullable = false)
    private String perfilExperiencia;

    @NotBlank(message = "El campo Estado no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estado;

    @NotBlank(message = "El campo Calificacion no puede estar vacío")
    @Column(length = 2, nullable = false)
    private Integer calificacion;
}
