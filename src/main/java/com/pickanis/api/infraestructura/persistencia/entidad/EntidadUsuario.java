package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios", uniqueConstraints = {@UniqueConstraint(name = "uk_usuarios_identificacion", columnNames = {"identificacion", "nombreUsuario"})})
public class EntidadUsuario {
    @Id
    @NotBlank(message = "El campo Identificación no puede estar vacío")
    @Column(length = 10, unique = true, nullable = false)
    private String identificacion;

    @NotBlank(message = "El campo Nombre no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombre;

    @NotBlank(message = "El campo Apellido no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String apellido;

    @NotBlank(message = "El campo correo no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String correo;

    @NotBlank(message = "El campo Usuario no puede estar vacío")
    @Column(length = 30, nullable = false, unique = true)
    private String nombreUsuario;

    @NotBlank(message = "El campo Contraseña no puede estar vacío")
    @Column(length = 60, nullable = false)
    private String contrasena;

    @Column(length = 100) //Dirreccion a la ubicacionde la imagen
    private String foto;

    private Boolean habilitado;

    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "rol_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "rol_id"})})
    private List<Roles> roles;
}
