package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.excepcion.ExcepcionDatosEntrada;
import lombok.Getter;

import java.util.List;

public enum TipoDocumento {
    CEDULA(1), PASAPORTE(2), TARJETA_IDENTIDAD(3);

    @Getter
    private final Integer tipoDocumento;

    TipoDocumento(Integer tipo) {
        this.tipoDocumento = tipo;
    }

    public static TipoDocumento buildEnum(Integer tipo) {
        switch (tipo) {
            case 1:
                return TipoDocumento.CEDULA;
            case 2:
                return TipoDocumento.PASAPORTE;
            case 3:
                return TipoDocumento.TARJETA_IDENTIDAD;
            default:
                throw new ExcepcionDatosEntrada(List.of("Tipo de documento no válido"));
        }
    }
}
