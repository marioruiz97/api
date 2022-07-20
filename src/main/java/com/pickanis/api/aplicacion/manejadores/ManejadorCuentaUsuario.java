package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.aplicacion.comandos.ComandoInformacionPersonal;
import com.pickanis.api.aplicacion.fabricas.FabricaCuentaUsuario;
import com.pickanis.api.dominio.servicios.ServicioCuentaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCuentaUsuario {
    private final ServicioCuentaUsuario servicioCuentaUsuario;
    private final FabricaCuentaUsuario fabricaCuentaUsuario;

    @Autowired
    public ManejadorCuentaUsuario(ServicioCuentaUsuario servicioCuentaUsuario, FabricaCuentaUsuario fabricaCuentaUsuario) {
        this.servicioCuentaUsuario = servicioCuentaUsuario;
        this.fabricaCuentaUsuario = fabricaCuentaUsuario;
    }

    public ComandoInformacionPersonal obtenerMiPerfil(String nombreUsuario) {
        if (this.servicioCuentaUsuario.esPaseador(nombreUsuario)) {
            return this.fabricaCuentaUsuario.construirPerfilPaseador(this.servicioCuentaUsuario.obtenerPerfilPaseador(nombreUsuario));
        } else {
            return this.fabricaCuentaUsuario.construirPerfilUsuario(this.servicioCuentaUsuario.obtenerMiPerfil(nombreUsuario));
        }
    }
}
