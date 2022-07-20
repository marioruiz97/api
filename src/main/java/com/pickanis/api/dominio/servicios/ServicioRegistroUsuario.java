package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionUsuarioRegistrado;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import com.pickanis.api.dominio.repositorio.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioRegistroUsuario {

    private final RepositorioRegistroUsuario repositorioUsuario;
    private final RepositorioRegistroPaseador repositorioPaseador;
    private final RepositorioRoles repositorioRoles;

    @Autowired
    public ServicioRegistroUsuario(RepositorioRegistroUsuario repositorioUsuario, RepositorioRegistroPaseador repositorioPaseador, RepositorioRoles repositorioRoles) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioPaseador = repositorioPaseador;
        this.repositorioRoles = repositorioRoles;
    }

    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        validarDatosIdentificacionUsuario(nuevoUsuario);
        Usuario usuarioRegistrado = repositorioUsuario.registrarUsuario(nuevoUsuario, repositorioRoles.obtenerRolUsuario());
        System.out.println(String.format("El usuario %s se ha registrado correctamente", usuarioRegistrado.getNombreUsuario()));
        return usuarioRegistrado;
    }

    public Usuario registrarPaseador(Paseador nuevoPaseador) {
        Usuario nuevoUsuario = registrarUsuarioPaseador(nuevoPaseador.getUsuario());
        System.out.println(String.format("El usuario %s se ha registrado correctamente", nuevoUsuario.getNombreUsuario()));
        nuevoPaseador.activarPaseador();
        repositorioPaseador.registrarPaseador(nuevoPaseador);
        System.out.println(String.format("El paseador %s se ha registrado correctamente", nuevoUsuario.getNombreUsuario()));
        return nuevoUsuario;
    }


    private Usuario registrarUsuarioPaseador(Usuario usuario) {
        validarDatosIdentificacionUsuario(usuario);
        return repositorioUsuario.registrarUsuarioPaseador(usuario, repositorioRoles.obtenerRolPaseador());
    }

    private void validarDatosIdentificacionUsuario(Usuario nuevoUsuario) {
        if (repositorioUsuario.buscarUsuarioPorNombreUsuario(nuevoUsuario.getNombreUsuario()) != null)
            throw new ExcepcionUsuarioRegistrado("nombre de usuario");

        if (repositorioUsuario.buscarUsuarioPorCorreo(nuevoUsuario.getCorreo()) != null)
            throw new ExcepcionUsuarioRegistrado("correo electrónico");

        if (repositorioUsuario.buscarUsuarioPorIdentificacion(nuevoUsuario.getIdentificacion()) != null)
            throw new ExcepcionUsuarioRegistrado("identificacion");
    }

}
