package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.ComandoInformacionPersonal;
import com.pickanis.api.aplicacion.manejadores.ManejadorCuentaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("mi-cuenta")
public class ControladorCuentaUsuario extends ControladorBase {

    private final ManejadorCuentaUsuario manejadorCuentaUsuario;

    @Autowired
    public ControladorCuentaUsuario(ManejadorCuentaUsuario manejadorCuentaUsuario) {
        this.manejadorCuentaUsuario = manejadorCuentaUsuario;
    }

    @GetMapping
    public ResponseEntity<?> obtenerMiInformacion() {
        ComandoInformacionPersonal perfil = this.manejadorCuentaUsuario.obtenerMiPerfil(obtenerUsuarioEnSesion());
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }
}
