package com.pickanis.api.dominio.modelo;


import lombok.Getter;

public enum SexoMascota {
    M("MACHO"), H("HEMBRA");

    @Getter
    private final String sexo;

    SexoMascota(String sexo) {
        this.sexo = sexo;
    }
}
