package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.ComandoConsultaInformacionPersonal;
import com.pickanis.api.aplicacion.comandos.ComandoGuardarInformacionPersonal;
import com.pickanis.api.aplicacion.manejadores.ManejadorCuentaUsuario;
import com.pickanis.api.dominio.excepcion.ExcepcionDatosExpuestos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
        ComandoConsultaInformacionPersonal perfil = this.manejadorCuentaUsuario.obtenerMiPerfil(obtenerUsuarioEnSesion());
        return new ResponseEntity<>(perfil, HttpStatus.OK);
    }

    @PostMapping("/info-personal/{identificacion}")
    public ResponseEntity<?> guardarDatosPersonales(@Valid @RequestBody ComandoGuardarInformacionPersonal informacion, BindingResult bindingResult,
                                                    @PathVariable String identificacion) {
        if (!identificacion.equals(informacion.getIdentificacion()))
            throw new ExcepcionDatosExpuestos();
        validarDatosEntrada(bindingResult);
        String nombreUsuario = obtenerUsuarioEnSesion();
        this.manejadorCuentaUsuario.guardarMisDatosPersonales(informacion, nombreUsuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
