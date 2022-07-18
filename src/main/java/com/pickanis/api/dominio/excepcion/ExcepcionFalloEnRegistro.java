package com.pickanis.api.dominio.excepcion;

public class ExcepcionFalloEnRegistro extends RuntimeException {

    public ExcepcionFalloEnRegistro(String mensaje) {
        super(mensaje);
    }
}
