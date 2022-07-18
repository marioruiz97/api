package com.pickanis.api.dominio.repositorio;


import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;

public interface RepositorioRegistroUsuario {

    Usuario registrarUsuario(Usuario usuario, Roles rolUsuario);

    Usuario buscarUsuarioPorCorreo(String correo);

    Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario);

    Usuario buscarUsuarioPorIdentificacion(String identificacion);

    Usuario registrarUsuarioPaseador(Usuario usuario, Roles rolPaseador);
}