package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.aplicacion.comandos.ComandoRegistro;
import com.pickanis.api.aplicacion.fabricas.FabricaUsuario;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.servicios.ServicioRegistroUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistroUsuario {


    private final ServicioRegistroUsuario servicioRegistroUsuario;
    private final FabricaUsuario fabricaUsuario;

    @Autowired
    public ManejadorRegistroUsuario(ServicioRegistroUsuario servicioRegistroUsuario, FabricaUsuario fabricaUsuario) {
        this.servicioRegistroUsuario = servicioRegistroUsuario;
        this.fabricaUsuario = fabricaUsuario;
    }

    public void registrarUsuario(ComandoRegistro nuevoRegistro) {
        servicioRegistroUsuario.registrarUsuario(fabricaUsuario.construir(nuevoRegistro));
    }

    public void registrarPaseador(ComandoRegistro nuevoRegistro) {
        Usuario usuario = fabricaUsuario.construir(nuevoRegistro);
        Paseador usuarioPaseador = fabricaUsuario.construirPaseador(usuario, nuevoRegistro);
        servicioRegistroUsuario.registrarPaseador(usuarioPaseador);
    }
}
