package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioRoles extends JpaRepository<Roles, Long> {

    String ROL_USUARIO = "ROLE_USUARIO";
    String ROL_PASEADOR = "ROLE_PASEADOR";

    default Roles obtenerRolUsuario() {
        return findByNombreRol(ROL_USUARIO).orElse(null);
    }

    default Roles obtenerRolPaseador() {
        return findByNombreRol(ROL_PASEADOR).orElse(null);
    }
    Optional<Roles> findByNombreRol(String nombreRol);
}
