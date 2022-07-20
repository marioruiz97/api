package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;

public interface RepositorioPaseador {

    boolean esPaseador(String identificacion);

    Paseador obtenerPaseadorPorIdentificacion(String identificacion);
}
