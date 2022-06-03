package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contactoUsuarios", uniqueConstraints = {@UniqueConstraint(name = "uk_contactoUsuarios_identificacion", columnNames = {"idContactoUsuarios"})})
public class EntidadContactoUsuario {

    @Id
    @NotBlank(message = "no puede estar vacío")
    @Column(length = 10, unique = true, nullable = false)
    private Integer idContactoUsuarios;

    @OneToOne(optional = false)
    @JoinColumn(name = "identificacion_Usuario", referencedColumnName = "identificacion")
    private EntidadUsuario usuarioCalificado;

    @NotBlank(message = "El campo Celular no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String celular;

    @NotBlank(message = "El Campo Teléfono Fijo no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String telefonoFijo;

    @NotBlank(message = "El campo Dirrección no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String direccion;

    @NotBlank(message = "El campo Email no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String Email;

    @NotBlank(message = "El campo Celular Emergencia no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String celularEmergencia;

    @NotBlank(message = "El campo Teléfono Fijo Emergencia no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String telefonoFijoEmergencia;
}
