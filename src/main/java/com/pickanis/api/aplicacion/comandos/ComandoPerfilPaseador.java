package com.pickanis.api.aplicacion.comandos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class ComandoPerfilPaseador {

    @Setter
    private String nombreUsuario;

    @NotBlank(message = "El campo Tiempo de Experiencia no puede estar vacío")
    @Size(min = 1, max = 100)
    private String tiempoExperiencia;

    @NotBlank(message = "El campo Tiempo de Experiencia no puede estar vacío")
    @Size(min = 1, max = 250)
    private String perfil;

}
