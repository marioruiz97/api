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
@Table(name = "contacto_usuario")
public class EntidadContactoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idContactoUsuario;

    @NotBlank(message = "El campo Celular no puede estar vacío")
    @Column(length = 11, nullable = false)
    @Pattern(regexp = "(^$|[0-9]{7,11})")
    private String celular;

    @NotBlank(message = "El Campo Teléfono Fijo no puede estar vacío")
    @Column(length = 11, nullable = false)
    @Pattern(regexp = "(^$|[0-9]{7,11})")
    private String telefonoFijo;

    @NotBlank(message = "El campo Dirrección no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String direccion;

}
