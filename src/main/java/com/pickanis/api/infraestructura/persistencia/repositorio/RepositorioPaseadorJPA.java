package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.repositorio.RepositorioPaseador;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioPaseadorJPA extends RepositorioPaseador, JpaRepository<EntidadPaseador, String> {

    @Override
    default Paseador obtenerPaseadorPorIdentificacion(String identificacion) {
        return ConvertidorPaseador.convertirADominio(findByIdPaseadorIgnoreCase(identificacion).orElse(null));
    }

    @Override
    default boolean esPaseador(String identificacion) {
        return existsByIdPaseadorEqualsIgnoreCase(identificacion);
    }

    Boolean existsByIdPaseadorEqualsIgnoreCase(String idPaseador);

    Optional<EntidadPaseador> findByIdPaseadorIgnoreCase(String idPaseador);
}
