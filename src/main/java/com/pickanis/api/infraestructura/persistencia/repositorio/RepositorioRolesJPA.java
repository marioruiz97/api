package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.repositorio.RepositorioRoles;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioRolesJPA extends JpaRepository<Roles, Long>, RepositorioRoles {

    String ROL_USUARIO = "ROLE_USUARIO";
    String ROL_PASEADOR = "ROLE_PASEADOR";

    @Override
    default Roles obtenerRolUsuario() {
        return findByNombreRol(ROL_USUARIO).orElse(null);
    }

    @Override
    default Roles obtenerRolPaseador() {
        return findByNombreRol(ROL_PASEADOR).orElse(null);
    }

    Optional<Roles> findByNombreRol(String nombreRol);
}
