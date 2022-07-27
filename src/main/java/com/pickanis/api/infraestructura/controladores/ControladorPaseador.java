package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.ComandoPerfilPaseador;
import com.pickanis.api.aplicacion.manejadores.ManejadorPaseador;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.infraestructura.api.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("paseadores")
public class ControladorPaseador extends ControladorBase {

    private final ManejadorPaseador manejadorPaseador;

    @Autowired
    public ControladorPaseador(ManejadorPaseador manejadorPaseador) {
        this.manejadorPaseador = manejadorPaseador;
    }


    @GetMapping
    public ResponseEntity<List<Paseador>> obtenerPaseadores() {
        List<Paseador> lista = this.manejadorPaseador.obtenerPaseadores();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/mi-info")
    public ResponseEntity<Paseador> obtenerMiPerfil() {
        Paseador paseador = this.manejadorPaseador.obtenerMiPerfil(obtenerUsuarioEnSesion());
        return new ResponseEntity<>(paseador, HttpStatus.OK);
    }

    @GetMapping("/es-paseador")
    public ResponseEntity<Boolean> esPaseador() {
        Boolean esPaseador = this.manejadorPaseador.esPaseador(obtenerUsuarioEnSesion());
        return new ResponseEntity<>(esPaseador, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Respuesta> registrarPaseador(@Valid @RequestBody ComandoPerfilPaseador comando, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        comando.setNombreUsuario(obtenerUsuarioEnSesion());
        this.manejadorPaseador.registrarPaseador(comando);
        String mensaje = String.format("Se registró el usuario %s como paseador", comando.getNombreUsuario());
        System.out.println(mensaje);
        Respuesta respuesta = new Respuesta(mensaje, true);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Respuesta> editarPerfilPaseador(@Valid @RequestBody ComandoPerfilPaseador comando, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        comando.setNombreUsuario(obtenerUsuarioEnSesion());
        this.manejadorPaseador.editarPaseador(comando);
        String mensaje = String.format("Se editó el perfil de paseador del usuario %s", comando.getNombreUsuario());
        System.out.println(mensaje);
        Respuesta respuesta = new Respuesta(mensaje, true);
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/desactivar")
    public ResponseEntity<Respuesta> desactivarPaseador() {
        String nombreUsuario = obtenerUsuarioEnSesion();
        Boolean esPaseador = this.manejadorPaseador.desactivarPaseador(nombreUsuario);
        Respuesta respuesta = new Respuesta(String.format("No se pudo desactivar el perfil de paseador de %s", nombreUsuario), false);
        if (!esPaseador)
            respuesta = new Respuesta(String.format("Se desactivó el perfil de paseador de %s", nombreUsuario), true);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}
