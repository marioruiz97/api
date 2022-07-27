package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;

import java.util.List;

public interface RepositorioPaseador {

    boolean esPaseador(String identificacion);

    Paseador obtenerPaseadorPorIdentificacion(String identificacion);

    void desactivarPaseador(String identificacion);

    void guardarPerfilPaseador(Usuario usuario, String perfil, String tiempoExperiencia);

    List<Paseador> obtenerPaseadores();
}
