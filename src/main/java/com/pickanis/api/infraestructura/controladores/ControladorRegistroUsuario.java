package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.ComandoRegistro;
import com.pickanis.api.aplicacion.manejadores.ManejadorRegistroUsuario;
import com.pickanis.api.infraestructura.api.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("cuenta/registro")
public class ControladorRegistroUsuario extends ControladorBase {

    public static final String SE_HA_REGISTRADO_EL_USUARIO_S_CON_ÉXITO = "Se ha registrado el usuario %s con éxito";
    private static final String SE_HA_REGISTRADO_EL_PASEADOR_CON_ÉXITO = "Se ha registrado el usuario %s como paseador de forma exitosa";
    private final ManejadorRegistroUsuario manejadorRegistroUsuario;

    @Autowired
    public ControladorRegistroUsuario(ManejadorRegistroUsuario manejadorRegistroUsuario) {
        this.manejadorRegistroUsuario = manejadorRegistroUsuario;
    }

    @PostMapping
    public ResponseEntity<Respuesta> nuevoRegistro(@Valid @RequestBody ComandoRegistro nuevoRegistro, @RequestParam String tipoRegistro, BindingResult bindingResult) {
        if (tipoRegistro.equals("paseador")) {
            return registrarPaseador(nuevoRegistro, bindingResult);
        } else {
            return registrarUsuario(nuevoRegistro, bindingResult);
        }
    }


    private ResponseEntity<Respuesta> registrarUsuario(@Valid ComandoRegistro nuevoRegistro, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        manejadorRegistroUsuario.registrarUsuario(nuevoRegistro);
        String mensaje = String.format(SE_HA_REGISTRADO_EL_USUARIO_S_CON_ÉXITO, nuevoRegistro.getUsuario());
        return new ResponseEntity<>(new Respuesta(mensaje, true), HttpStatus.CREATED);
    }

    private ResponseEntity<Respuesta> registrarPaseador(@Valid ComandoRegistro nuevoRegistro, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        nuevoRegistro.setEstado("1"); // TODO: este codigo se puso para que funcione, pero no estoy seguro que es el campo estado, debería eliminarse?
        manejadorRegistroUsuario.registrarPaseador(nuevoRegistro);
        String mensaje = String.format(SE_HA_REGISTRADO_EL_PASEADOR_CON_ÉXITO, nuevoRegistro.getUsuario());
        return new ResponseEntity<>(new Respuesta(mensaje, true), HttpStatus.CREATED);
    }

}
