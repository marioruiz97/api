package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.Registro;
import com.pickanis.api.aplicacion.manejadores.ManejadorRegistroUsuario;
import com.pickanis.api.infraestructura.api.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("cuenta/registro")
@CrossOrigin(value = "*")
public class ControladorRegistroUsuario extends ControladorBase {

    private final ManejadorRegistroUsuario manejadorRegistroUsuario;

    @Autowired
    public ControladorRegistroUsuario(ManejadorRegistroUsuario manejadorRegistroUsuario) {
        this.manejadorRegistroUsuario = manejadorRegistroUsuario;
    }

    @PostMapping
    public ResponseEntity<String> registrarUsuario(@Valid Registro nuevoRegistro, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        manejadorRegistroUsuario.registrarUsuario(nuevoRegistro);
        return new ResponseEntity<>("El usuario se ha registrado correctamente", HttpStatus.CREATED);

    }

}
