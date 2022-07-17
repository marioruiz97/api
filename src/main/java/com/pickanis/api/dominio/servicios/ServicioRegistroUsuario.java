package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionUsuarioRegistrado;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistroUsuario {

    private final RepositorioRegistroUsuario repositorioUsuario;
    private final RepositorioRegistroPaseador repositorioPaseador;

    @Autowired
    public ServicioRegistroUsuario(RepositorioRegistroUsuario repositorioUsuario, RepositorioRegistroPaseador repositorioPaseador) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioPaseador = repositorioPaseador;
    }

    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        if (repositorioUsuario.buscarUsuarioPorNombreUsuario(nuevoUsuario.getNombreUsuario()) != null)
            throw new ExcepcionUsuarioRegistrado("nombre de usuario");

        if (repositorioUsuario.buscarUsuarioPorCorreo(nuevoUsuario.getCorreo()) != null)
            throw new ExcepcionUsuarioRegistrado("correo electrónico");

        if (repositorioUsuario.buscarUsuarioPorIdentificacion(nuevoUsuario.getIdentificacion()) != null)
            throw new ExcepcionUsuarioRegistrado("identificacion");

        return repositorioUsuario.registrarUsuario(nuevoUsuario);
    }

    public Usuario registrarPaseador(Paseador nuevoPaseador) {
        Usuario nuevoUsuario = registrarUsuario(nuevoPaseador.getUsuario());
        repositorioPaseador.registrarPaseador(nuevoPaseador);
        return nuevoUsuario;
    }
}
