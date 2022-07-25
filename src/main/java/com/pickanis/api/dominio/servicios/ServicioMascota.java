package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionDatosExpuestos;
import com.pickanis.api.dominio.modelo.Mascota;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioCuentaUsuario;
import com.pickanis.api.dominio.repositorio.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioMascota {

    private final RepositorioCuentaUsuario repositorioUsuario;
    private final RepositorioMascota repositorioMascota;

    @Autowired
    public ServicioMascota(RepositorioCuentaUsuario repositorioUsuario, RepositorioMascota repositorioMascota) {
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioMascota = repositorioMascota;
    }

    public Mascota crearOEditarMascota(Mascota mascota, String nombreUsuario) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        if (!mascota.getDueno().equals(usuario.getIdentificacion())) throw new ExcepcionDatosExpuestos();
        return this.repositorioMascota.crearOEditarMascota(mascota);
    }

    public List<Mascota> obtenerMisMascotas(String nombreUsuario) {
        if (this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario) == null)
            throw new UsernameNotFoundException(String.format("No se encontró el usuario %s", nombreUsuario));
        return this.repositorioMascota.obtenerMisMascotas(nombreUsuario);
    }

    public void eliminarMascota(Long idMascota, String nombreUsuario) {
        Usuario usuario = this.repositorioUsuario.obtenerUsuarioPorNombreUsuario(nombreUsuario);
        Mascota mascota = this.repositorioMascota.obtenerMascotaPorId(idMascota);
        if (!mascota.getDueno().equals(usuario.getIdentificacion())) throw new ExcepcionDatosExpuestos();
        this.repositorioMascota.eliminarMascota(idMascota);
    }
}
