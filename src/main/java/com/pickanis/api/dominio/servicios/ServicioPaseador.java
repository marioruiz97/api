package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioCuentaUsuario;
import com.pickanis.api.dominio.repositorio.RepositorioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioPaseador {
    private final RepositorioPaseador repositorioPaseador;
    private final RepositorioCuentaUsuario repositorioUsuario;

    @Autowired
    public ServicioPaseador(RepositorioPaseador repositorioPaseador, RepositorioCuentaUsuario repositorioUsuario) {
        this.repositorioPaseador = repositorioPaseador;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Paseador obtenerMiPerfil(String nombreUsuario) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        return this.repositorioPaseador.obtenerPaseadorPorIdentificacion(usuario.getIdentificacion());
    }

    public Boolean esPaseador(String nombreUsuario) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        Paseador paseador = this.repositorioPaseador.obtenerPaseadorPorIdentificacion(usuario.getIdentificacion());
        return paseador != null && paseador.getEstado();
    }

    public Boolean desactivarPaseador(String nombreUsuario) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        this.repositorioPaseador.desactivarPaseador(usuario.getIdentificacion());
        return this.esPaseador(nombreUsuario);
    }

    public void registrarOEditarPerfilPaseador(String nombreUsuario, String perfil, String tiempoExperiencia) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        this.repositorioPaseador.guardarPerfilPaseador(usuario, perfil, tiempoExperiencia);
    }

}
