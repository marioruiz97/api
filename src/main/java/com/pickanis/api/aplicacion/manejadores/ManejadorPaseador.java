package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.aplicacion.comandos.ComandoPerfilPaseador;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.servicios.ServicioPaseador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorPaseador {
    private final ServicioPaseador servicioPaseador;

    @Autowired
    public ManejadorPaseador(ServicioPaseador servicioPaseador) {
        this.servicioPaseador = servicioPaseador;
    }

    public Paseador obtenerMiPerfil(String nombreUsuario) {
        return this.servicioPaseador.obtenerMiPerfil(nombreUsuario);
    }

    public Boolean esPaseador(String nombreUsuario) {
        return this.servicioPaseador.esPaseador(nombreUsuario);
    }

    public Boolean desactivarPaseador(String nombreUsuario) {
        return this.servicioPaseador.desactivarPaseador(nombreUsuario);
    }

    public void registrarPaseador(ComandoPerfilPaseador comando) {
        this.servicioPaseador.registrarOEditarPerfilPaseador(comando.getNombreUsuario(), comando.getPerfil(), comando.getTiempoExperiencia());
    }

    public void editarPaseador(ComandoPerfilPaseador comando) {
        this.servicioPaseador.registrarOEditarPerfilPaseador(comando.getNombreUsuario(), comando.getPerfil(), comando.getTiempoExperiencia());
    }

    public List<Paseador> obtenerPaseadores() {
        return this.servicioPaseador.obtenerPaseadores();
    }
}
