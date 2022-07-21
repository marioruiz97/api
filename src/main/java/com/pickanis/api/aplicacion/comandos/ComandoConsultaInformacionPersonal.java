package com.pickanis.api.aplicacion.comandos;

import com.pickanis.api.dominio.modelo.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ComandoConsultaInformacionPersonal {

    private Usuario usuario;
    private String tiempoExperiencia;
    private String perfilExperiencia;
    private Boolean estado;
    private final Boolean esPaseador;

    public ComandoConsultaInformacionPersonal(Usuario usuario, Boolean esPaseador) {
        this.usuario = usuario;
        this.esPaseador = esPaseador;
    }
}
