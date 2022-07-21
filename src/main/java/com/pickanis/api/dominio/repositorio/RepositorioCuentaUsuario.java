package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Usuario;

public interface RepositorioCuentaUsuario {

    String obtenerIdentificacion(String nombreUsuario);

    Usuario obtenerUsuarioPorIdentificacion(String identificacion);

    void guardarInformacionPersonal(Usuario usuario);
}
