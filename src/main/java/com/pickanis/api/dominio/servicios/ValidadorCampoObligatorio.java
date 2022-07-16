package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionCampoObligatorio;

public final class ValidadorCampoObligatorio {

    public static final String VALOR_INVALIDO = "El valor ingresado para el campo %s no corresponde al tipo de dato esperado";

    public static void validarCampoObligatorio(Object dato, String nombreDato) {
        boolean esNull = dato == null;
        boolean esString = !esNull && dato.getClass().equals(String.class);

        if (esNull || (esString && dato.equals("")))
            throw new ExcepcionCampoObligatorio(String.format(VALOR_INVALIDO, nombreDato));
    }

    public static void validarTextoConFormato() {

    }

}
