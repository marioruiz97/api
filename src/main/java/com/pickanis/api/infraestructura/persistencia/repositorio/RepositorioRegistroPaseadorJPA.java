package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioRegistroPaseadorJPA extends JpaRepository<EntidadPaseador, String> {

    default Long obtenerIdPorUsuario(EntidadUsuario usuario) {
        EntidadPaseador paseador = findByUsuario(usuario).orElse(null);
        return paseador != null ? paseador.getIdPaseador() : null;
    }

    Optional<EntidadPaseador> findByUsuario(EntidadUsuario usuario);
}
