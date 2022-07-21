package com.pickanis.api.aplicacion.comandos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class ComandoRegistro {

    @NotNull
    private Integer tipoDocumento;

    @NotBlank
    @Size(min = 6, max = 15)
    private String identificacion;

    @NotBlank
    @Size(min = 3, max = 75)
    private String nombre;

    @NotBlank
    @Size(min = 3, max = 75)
    private String apellidos;

    @NotBlank
    @Size(min = 5, max = 50)
    private String direccion;

    @NotBlank
    @Pattern(regexp = "(^$|[0-9]*)")//Regexp for phones
    @Size(min = 10, max = 11)
    private String telefono;

    @NotBlank
    @Pattern(regexp = "(^$|[0-9]*)")
    @Size(min = 10, max = 11)
    private String celular;

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    @Size(max = 15)
    private String nombreUsuario;

    @NotBlank
    private String contrasena;

    @Setter
    private String tiempoExperiencia;

    @Setter
    private String perfil;

}
