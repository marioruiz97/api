package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadMascota;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioMascotaJPA extends JpaRepository<EntidadMascota, Long> {

    List<EntidadMascota> findByDueno(EntidadUsuario dueno);

}
