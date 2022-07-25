package com.pickanis.api.infraestructura.controladores;

import com.pickanis.api.aplicacion.manejadores.ManejadorMascota;
import com.pickanis.api.dominio.excepcion.ExcepcionDatosExpuestos;
import com.pickanis.api.dominio.modelo.Mascota;
import com.pickanis.api.infraestructura.api.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("mascotas")
public class ControladorMascota extends ControladorBase {

    private final ManejadorMascota manejadorMascota;

    @Autowired
    public ControladorMascota(ManejadorMascota manejadorMascota) {
        this.manejadorMascota = manejadorMascota;
    }

    @GetMapping
    public List<Mascota> obtenerMisMascotas() {
        String nombreUsuario = obtenerUsuarioEnSesion();
        return this.manejadorMascota.ObtenerMisMascotas(nombreUsuario);
    }

    @PostMapping
    public ResponseEntity<Mascota> crearMascota(@Valid @RequestBody Mascota mascota, BindingResult bindingResult) {
        validarDatosEntrada(bindingResult);
        Mascota nuevaMascota = this.manejadorMascota.crearMascota(mascota, obtenerUsuarioEnSesion());
        return new ResponseEntity<>(nuevaMascota, HttpStatus.CREATED);
    }

    @PutMapping("/{identificacion}")
    public ResponseEntity<Mascota> editarMascota(@Valid @RequestBody Mascota mascota, BindingResult bindingResult, @PathVariable String identificacion) {
        validarDatosEntrada(bindingResult);
        if (!identificacion.equals(mascota.getDueno()) || mascota.getIdMascota() == null)
            throw new ExcepcionDatosExpuestos();
        Mascota mascotaEditada = this.manejadorMascota.editarMascota(mascota, obtenerUsuarioEnSesion());
        return new ResponseEntity<>(mascotaEditada, HttpStatus.OK);
    }

    @DeleteMapping("/{idMascota}")
    public ResponseEntity<Respuesta> eliminarMascota(@PathVariable Long idMascota) {
        this.manejadorMascota.eliminarMascota(idMascota, obtenerUsuarioEnSesion());
        String mensaje = "Se ha eliminado la mascota con éxito";
        return new ResponseEntity<>(new Respuesta(mensaje, true), HttpStatus.OK);
    }
}
