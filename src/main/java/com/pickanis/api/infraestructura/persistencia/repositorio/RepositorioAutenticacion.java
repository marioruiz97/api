package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface RepositorioAutenticacion extends CrudRepository<EntidadUsuario, Long> {

    Optional<EntidadUsuario> findByNombreUsuario(String nombreUsuario);

    Optional<EntidadUsuario> findByCorreoIgnoreCase(String nombreUsuario);
}
