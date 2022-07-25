package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Mascota;

import java.util.List;

public interface RepositorioMascota {

    Mascota crearOEditarMascota(Mascota mascota);

    List<Mascota> obtenerMisMascotas(String nombreUsuario);

    Mascota obtenerMascotaPorId(Long idMascota);

    void eliminarMascota(Long idMascota);
}
