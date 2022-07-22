package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Usuario;

public interface RepositorioCuentaUsuario {

    String obtenerIdentificacion(String nombreUsuario);

    Usuario obtenerUsuarioPorIdentificacion(String identificacion);

    Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario);

    void guardarInformacionPersonal(Usuario usuario);

    void desactivarUsuario(Usuario usuario);

    void cambiarContrasena(String nombreUsuario, String nuevaContrasena);
}
