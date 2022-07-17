package com.pickanis.api.dominio.repositorio;


import com.pickanis.api.dominio.modelo.Usuario;

public interface RepositorioRegistroUsuario {

    Usuario registrarUsuario(Usuario usuario);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario);

    Usuario buscarUsuarioPorIdentificacion(String identificacion);

}