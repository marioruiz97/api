package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioCuentaUsuario;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuarioJPA extends RepositorioCuentaUsuario, JpaRepository<EntidadUsuario, String> {

    @Override
    @Query(value = "select u.identificacion from usuarios u where u.nombre_usuario= :nombreUsuario", nativeQuery = true)
    String obtenerIdentificacion(@Param("nombreUsuario") String nombreUsuario);

    @Override
    default Usuario obtenerUsuarioPorIdentificacion(String identificacion) {
        return ConvertidorUsuario.convertirADominio(findByIdentificacionIgnoreCase(identificacion).orElse(null));
    }

    Optional<EntidadUsuario> findByIdentificacionIgnoreCase(String identificacion);
}
