package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.dominio.modelo.Mascota;
import com.pickanis.api.dominio.servicios.ServicioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorMascota {

    private final ServicioMascota servicioMascota;

    @Autowired
    public ManejadorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }

    public Mascota crearMascota(Mascota mascota, String nombreUsuario) {
        return this.servicioMascota.crearOEditarMascota(mascota, nombreUsuario);
    }

    public Mascota editarMascota(Mascota mascota, String nombreUsuario) {
        return this.servicioMascota.crearOEditarMascota(mascota, nombreUsuario);
    }

    public List<Mascota> ObtenerMisMascotas(String nombreUsuario) {
        return this.servicioMascota.obtenerMisMascotas(nombreUsuario);
    }

    public void eliminarMascota(Long idMascota, String nombreUsuario) {
        this.servicioMascota.eliminarMascota(idMascota, nombreUsuario);
    }
}
