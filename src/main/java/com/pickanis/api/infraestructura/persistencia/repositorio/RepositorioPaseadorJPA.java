package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioPaseadorJPA extends JpaRepository<EntidadPaseador, String> {

    Boolean existsByIdPaseadorEqualsIgnoreCase(String idPaseador);

    Optional<EntidadPaseador> findByIdPaseadorIgnoreCase(String idPaseador);

}
