package com.pickanis.api.dominio.servicios;


import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioCuentaUsuario;
import com.pickanis.api.dominio.repositorio.RepositorioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioCuentaUsuario {

    private static final String ERROR_USERNAME = "No se halló el nombre de usuario %s";
    private final RepositorioCuentaUsuario repositorio;
    private final RepositorioPaseador repositorioPaseador;

    @Autowired
    public ServicioCuentaUsuario(RepositorioCuentaUsuario repositorio, RepositorioPaseador repositorioPaseador) {
        this.repositorio = repositorio;
        this.repositorioPaseador = repositorioPaseador;
    }

    public Usuario obtenerMiPerfil(String nombreUsuario) {
        String identificacion = obtenerIdentificacion(nombreUsuario);
        return this.repositorio.obtenerUsuarioPorIdentificacion(identificacion);
    }

    public Paseador obtenerPerfilPaseador(String nombreUsuario) {
        String identificacion = obtenerIdentificacion(nombreUsuario);
        return this.repositorioPaseador.obtenerPaseadorPorIdentificacion(identificacion);
    }

    public boolean esPaseador(String nombreUsuario) {
        String identificacion = obtenerIdentificacion(nombreUsuario);
        return this.repositorioPaseador.esPaseador(identificacion);
    }

    private String obtenerIdentificacion(String nombreUsuario) {
        String identificacion = repositorio.obtenerIdentificacion(nombreUsuario);
        if (identificacion == null)
            throw new UsernameNotFoundException(String.format(ERROR_USERNAME, nombreUsuario));
        System.out.printf("Cargando perfil de %s con id: %s%n", nombreUsuario, identificacion);
        return identificacion;
    }

    public void guardarInformacionPersonal(Usuario nuevo, String nombreUsuario) {
        Usuario viejo = obtenerMiPerfil(nombreUsuario);
        this.repositorio.guardarInformacionPersonal(Usuario.actualizarDatos(nuevo, viejo));
    }
}
