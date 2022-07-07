package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.excepcion.ExcepcionDatosEntrada;
import lombok.Getter;

import java.util.Arrays;

public enum TipoDocumento {
    CEDULA("Cédula de Ciudadanía"), PASAPORTE("Pasaporte"), TARJETA_IDENTIDAD("Tarjeta de Identidad");

    @Getter
    private final String tipoDocumento;

    TipoDocumento(String tipo) {
        this.tipoDocumento = tipo;
    }

    public static TipoDocumento buildEnum(Integer tipo) throws ExcepcionDatosEntrada {
        switch (tipo) {
            case 1:
                return TipoDocumento.CEDULA;
            case 2:
                return TipoDocumento.PASAPORTE;
            case 3:
                return TipoDocumento.TARJETA_IDENTIDAD;
            default:
                throw new ExcepcionDatosEntrada(Arrays.asList("Tipo de documento no válido"));
        }
    }
}
