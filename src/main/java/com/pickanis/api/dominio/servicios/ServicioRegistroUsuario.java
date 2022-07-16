package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionUsuarioRegistrado;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistroUsuario {

    private final RepositorioRegistroUsuario repositorioRegistro;

    @Autowired
    public ServicioRegistroUsuario(RepositorioRegistroUsuario repositorioRegistro) {
        this.repositorioRegistro = repositorioRegistro;
    }

    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        if (repositorioRegistro.buscarUsuarioPorNombreUsuario(nuevoUsuario.getNombreUsuario()) != null)
            throw new ExcepcionUsuarioRegistrado("nombre de usuario");

        if (repositorioRegistro.buscarUsuarioPorCorreo(nuevoUsuario.getCorreo()) != null)
            throw new ExcepcionUsuarioRegistrado("correo electrónico");

        if (repositorioRegistro.buscarUsuarioPorIdentificacion(nuevoUsuario.getIdentificacion()) != null)
            throw new ExcepcionUsuarioRegistrado("identificacion");

        return repositorioRegistro.registrarUsuario(nuevoUsuario);
    }

    public Usuario registrarPaseador(Paseador nuevoPaseador) {
        Usuario nuevoUsuario = registrarUsuario(nuevoPaseador.getUsuario());
        repositorioRegistro.registrarPaseador(nuevoPaseador);
        return nuevoUsuario;
    }
}
