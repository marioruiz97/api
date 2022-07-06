package com.pickanis.api.dominio.excepcion;

import java.util.List;

public class ExcepcionDatosEntrada extends Exception {

    private final List<String> errores;

    public ExcepcionDatosEntrada(List<String> errores) {
        super();
        this.errores = errores;
    }
}
