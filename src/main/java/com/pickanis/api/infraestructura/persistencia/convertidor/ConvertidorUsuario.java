package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ConvertidorUsuario {
    private final RepositorioRoles repositorioRoles;

    @Autowired
    public ConvertidorUsuario(RepositorioRoles repositorioRoles) {
        this.repositorioRoles = repositorioRoles;
    }

    public Usuario convertirADominio(EntidadUsuario entidad) {
        return null;
    }

    public EntidadUsuario convertirAEntidad(Usuario dominio) {
        Roles rolUsuario = this.repositorioRoles.obtenerRolUsuario();
        return new EntidadUsuario(dominio.getIdentificacion(), dominio.getNombre(), dominio.getApellido(), dominio.getCorreo(), dominio.getNombreUsuario(), dominio.getContrasena(), dominio.getFoto(), dominio.getHabilitado(), Arrays.asList(rolUsuario));
    }
}
