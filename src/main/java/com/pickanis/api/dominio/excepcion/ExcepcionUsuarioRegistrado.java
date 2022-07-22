package com.pickanis.api.dominio.excepcion;

public class ExcepcionUsuarioRegistrado extends RuntimeException {


    private static final String MENSAJE = "Ya existe un usuario registrado con el/la %s ingresado, verifique los datos";

    public ExcepcionUsuarioRegistrado(String campo) {
        super(String.format(MENSAJE, campo));
    }
}
