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
    @NotBlank(message = "El campo Identificación no puede estar vacío")
    @Column(length = 10, unique = true, nullable = false)
    private Integer identificacion;

    @NotBlank(message = "El campo Nombre no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombre;

    @NotBlank(message = "El campo Apellido no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String apellido;

    @NotBlank(message = "El campo Usuario no puede estar vacío")
    @Column(length = 30, nullable = false, unique = true)
    private String nombreUsuario;

    @NotBlank(message = "El campo Contraseña no puede estar vacío")
    @Column(length = 15, nullable = false)
    @Pattern(regexp = "regexp para passwords") //todo:agregar regexp
    private String contrasena;

    @Column(length = 100) //Dirreccion a la ubicacionde la imagen
    private String foto;
}
