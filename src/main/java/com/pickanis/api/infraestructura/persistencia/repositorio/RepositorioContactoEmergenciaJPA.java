package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioContactoEmergenciaJPA extends JpaRepository<EntidadContactoEmergencia, Integer> {

    List<EntidadContactoEmergencia> findByUsuario(EntidadUsuario usuario);

}
