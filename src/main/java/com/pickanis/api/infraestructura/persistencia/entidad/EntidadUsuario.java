package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(name = "uk_usuarios_identificacion", columnNames = {"identificacion", "nombreUsuario"})})
public class EntidadUsuario {


    @Id
    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, unique = true, nullable = false)
    private Integer identificacion;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombre;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String apellido;


    private LocalDate fechaNacimiento; // todo: en verdad se necesita este campo?

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String celular;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String telefonoFijo;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String direccion;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String celularEmergencia;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String telefonoFijoEmergencia;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 30, nullable = false, unique = true)
    private String nombreUsuario;

    @NotBlank(message = "no puede estar vacío")
    @Column(length = 15, nullable = false)
    @Pattern(regexp = "regexp para passwords") //todo:agregar regexp
    private String contrasena;
}
