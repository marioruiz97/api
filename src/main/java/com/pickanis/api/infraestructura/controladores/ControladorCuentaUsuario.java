package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.comandos.ComandoConsultaInformacionPersonal;
import com.pickanis.api.aplicacion.comandos.ComandoGuardarInformacionPersonal;
import com.pickanis.api.aplicacion.manejadores.ManejadorCuentaUsuario;
import com.pickanis.api.dominio.excepcion.ExcepcionDatosExpuestos;
import com.pickanis.api.dominio.modelo.ContactoEmergencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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

    @GetMapping("/contactos")
    public List<ContactoEmergencia> obtenerContactosDeEmergencia(){
        String nombreUsuario = obtenerUsuarioEnSesion();
        return this.manejadorCuentaUsuario.obtenerMisContactosDeEmergencia(nombreUsuario);
    }

    @PostMapping("/contactos")
    public ResponseEntity<ContactoEmergencia> agregarContactoEmergencia(@Valid @RequestBody ContactoEmergencia contacto, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        String nombreUsuario = obtenerUsuarioEnSesion();
        ContactoEmergencia nuevoContacto = this.manejadorCuentaUsuario.agregarContactoEmergencia(contacto, nombreUsuario);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

    @PutMapping("/contactos/{id}")
    public ResponseEntity<ContactoEmergencia> editarContactoEmergencia(@Valid @RequestBody ContactoEmergencia contacto, BindingResult bindingResult, @PathVariable Integer id) {
        if (id == null || id == 0) {
            throw new IllegalArgumentException("El id ingresado no es válido");
        }
        validarDatosEntrada(bindingResult);
        String nombreUsuario = obtenerUsuarioEnSesion();
        contacto.setId(id);
        ContactoEmergencia nuevoContacto = this.manejadorCuentaUsuario.agregarContactoEmergencia(contacto, nombreUsuario);
        return new ResponseEntity<>(nuevoContacto, HttpStatus.CREATED);
    }

}
