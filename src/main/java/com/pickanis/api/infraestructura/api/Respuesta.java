package com.pickanis.api.infraestructura.api;

import lombok.Getter;

@Getter
public class Respuesta {

    private final String mensaje;
    private final Boolean exito;

    public Respuesta(String mensaje, Boolean exito) {
        this.mensaje = mensaje;
        this.exito = exito;
    }
}
