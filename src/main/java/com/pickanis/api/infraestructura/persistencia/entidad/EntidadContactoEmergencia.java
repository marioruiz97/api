package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contactos_emergencia")
public class EntidadContactoEmergencia implements Serializable {

    @Id
    @OneToOne
    @JoinColumn(name = "identificacion_usuario", referencedColumnName = "identificacion")
    private EntidadUsuario usuario;

    @NotBlank(message = "El campo nombre contacto emergencia no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombreContacto;

    @NotBlank(message = "El campo Celular Emergencia no puede estar vacío")
    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String celularEmergencia;

    @Column(length = 10, nullable = false)
    @Pattern(regexp = "regex") //todo: agregar regexp para telefonos
    private String telefonoFijoEmergencia;

}
