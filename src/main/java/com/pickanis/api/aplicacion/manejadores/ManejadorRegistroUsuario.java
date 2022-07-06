package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.aplicacion.comandos.Registro;
import com.pickanis.api.aplicacion.fabricas.FabricaUsuario;
import com.pickanis.api.dominio.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRegistroUsuario {


    private final ServicioUsuario servicioRegistroUsuario;
    private final FabricaUsuario fabricaUsuario;

    @Autowired
    public ManejadorRegistroUsuario(ServicioUsuario servicioRegistroUsuario, FabricaUsuario fabricaUsuario) {
        this.servicioRegistroUsuario = servicioRegistroUsuario;
        this.fabricaUsuario = fabricaUsuario;
    }

    public void registrarUsuario(Registro nuevoRegistro) {
        servicioRegistroUsuario.registrarUsuario(fabricaUsuario.construir(nuevoRegistro));
    }
}
