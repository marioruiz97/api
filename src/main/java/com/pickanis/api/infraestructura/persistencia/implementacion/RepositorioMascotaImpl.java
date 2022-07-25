package com.pickanis.api.infraestructura.persistencia.implementacion;

import com.pickanis.api.dominio.excepcion.ExcepcionMascotaNoEncontrada;
import com.pickanis.api.dominio.modelo.Mascota;
import com.pickanis.api.dominio.repositorio.RepositorioMascota;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorMascota;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadMascota;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioMascotaJPA;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioMascotaImpl implements RepositorioMascota {

    private final RepositorioMascotaJPA repositorioJPA;
    private final RepositorioUsuarioJPA repositorioUsuarioJPA;

    @Autowired
    public RepositorioMascotaImpl(RepositorioMascotaJPA repositorioJPA, RepositorioUsuarioJPA repositorioUsuarioJPA) {
        this.repositorioJPA = repositorioJPA;
        this.repositorioUsuarioJPA = repositorioUsuarioJPA;
    }

    @Override
    public Mascota crearOEditarMascota(Mascota mascota) {
        EntidadUsuario usuario = this.repositorioUsuarioJPA.findByIdentificacionIgnoreCase(mascota.getDueno())
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario para asociar a la mascota"));
        EntidadMascota entidadMascota = ConvertidorMascota.convertirAEntidad(mascota, usuario);
        return ConvertidorMascota.convertirADominio(repositorioJPA.save(entidadMascota));
    }

    @Override
    public List<Mascota> obtenerMisMascotas(String nombreUsuario) {
        EntidadUsuario usuario = this.repositorioUsuarioJPA.findByNombreUsuarioIgnoreCase(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró el usuario en sesión"));
        return this.repositorioJPA.findByDueno(usuario).stream().map(ConvertidorMascota::convertirADominio).collect(Collectors.toList());
    }

    @Override
    public Mascota obtenerMascotaPorId(Long idMascota) {
        return ConvertidorMascota.convertirADominio(this.repositorioJPA.findById(idMascota)
                .orElseThrow(() -> new ExcepcionMascotaNoEncontrada(idMascota)));
    }

    @Override
    public void eliminarMascota(Long idMascota) {
        this.repositorioJPA.deleteById(idMascota);
    }
}
