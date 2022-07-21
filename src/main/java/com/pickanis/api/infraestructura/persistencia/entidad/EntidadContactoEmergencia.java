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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "identificacion_usuario", referencedColumnName = "identificacion")
    private EntidadUsuario usuario;

    @NotBlank(message = "El campo nombre contacto emergencia no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String nombre;

    @NotBlank(message = "El campo Celular Emergencia no puede estar vacío")
    @Column(length = 12, nullable = false)
    @Pattern(regexp = "(^$|[0-9]{7,12})")
    private String celular;

    @Column(length = 12)
    @Pattern(regexp = "(^$|[0-9]{7,12})")
    private String telefonoFijo;

}
