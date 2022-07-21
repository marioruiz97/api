package com.pickanis.api.aplicacion.comandos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@AllArgsConstructor
@Getter
public class ComandoGuardarInformacionPersonal {

    @NotBlank
    @Size(min = 3, max = 75)
    private String nombre;
    @NotBlank
    @Size(min = 3, max = 75)
    private String apellido;
    @NotBlank
    @Size(min = 5, max = 50)
    private String direccion;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]*)")//Regexp for phones
    @Size(min = 10, max = 11)
    private String telefonoFijo;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]*)")//Regexp for phones
    @Size(min = 10, max = 11)
    private String celular;
    @NotBlank
    @Size(max = 15)
    private String nombreUsuario;
    @NotBlank
    @Email
    private String correo;
    @NotBlank
    @Size(min = 6, max = 15)
    private String identificacion;
    @NotNull
    private Integer tipoDocumento;

}
