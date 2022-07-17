package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ConvertidorPaseador {
    private final RepositorioRoles repositorioRoles;
    private final ConvertidorUsuario convertidorUsuario;
    private final RepositorioRegistroPaseador repositorioPaseador;

    @Autowired
    public ConvertidorPaseador(RepositorioRoles repositorioRoles, ConvertidorUsuario convertidorUsuario, RepositorioRegistroPaseador repositorioPaseador) {
        this.repositorioRoles = repositorioRoles;
        this.convertidorUsuario = convertidorUsuario;
        this.repositorioPaseador = repositorioPaseador;
    }


    public Paseador convertirADominio(EntidadPaseador entidad) {
        return null;
    }

    public EntidadPaseador convertirAEntidad(Paseador dominio) {
        Roles rolPaseador = this.repositorioRoles.obtenerRolPaseador();
        EntidadUsuario usuario = convertidorUsuario.convertirAEntidad(dominio.getUsuario());
        usuario.setRoles(Arrays.asList(rolPaseador));
        Long id = repositorioPaseador.obtenerIdPorUsuario(usuario);
        return new EntidadPaseador(id, usuario, dominio.getTiempoExperiencia(), dominio.getPerfilExperiencia(), dominio.getEstado(), dominio.getCalificacion());
    }

}