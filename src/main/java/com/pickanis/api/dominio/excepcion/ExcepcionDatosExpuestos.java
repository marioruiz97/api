package com.pickanis.api.dominio.excepcion;

public class ExcepcionDatosExpuestos extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Los datos recibidos en la peticion no son consistentes y pueden estar corruptos, por favor verifíquelos";

    public ExcepcionDatosExpuestos() {
        super(DEFAULT_MESSAGE);
    }
}
