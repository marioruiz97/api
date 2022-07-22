package com.pickanis.api.aplicacion.comandos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class ComandoCambioContrasena {

    @Size(max = 15)
    @NotBlank
    private String identificacion;
    @NotBlank
    @Size(min = 6, max = 14)
    private String contrasena;

}
