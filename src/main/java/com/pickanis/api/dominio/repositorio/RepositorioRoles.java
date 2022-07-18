package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.infraestructura.persistencia.entidad.Roles;

public interface RepositorioRoles {

    Roles obtenerRolUsuario();

    Roles obtenerRolPaseador();
}
