package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRegistroPaseadorJPA extends JpaRepository<EntidadPaseador, String>, RepositorioRegistroPaseador {

    @Override
    default Paseador registrarPaseador(Paseador nuevoPaseador) {
        return null;
    }
}
