package com.pickanis.api.infraestructura.persistencia.implementacion;

import com.pickanis.api.dominio.excepcion.ExcepcionFalloEnRegistro;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.dominio.repositorio.RepositorioRoles;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroPaseadorJPA;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioRegistroPaseadorImpl implements RepositorioRegistroPaseador {

    private static final String FALLO_EN_REGISTRO = "Falló el registro como paseador, no se pudo encontrar el usuario asociado";

    private final RepositorioRegistroPaseadorJPA repositorio;
    private final RepositorioRegistroUsuarioJPA repositorioUsuario;
    private final RepositorioRoles repositorioRoles;

    @Autowired
    public RepositorioRegistroPaseadorImpl(RepositorioRegistroPaseadorJPA repositorio, RepositorioRegistroUsuarioJPA repositorioUsuario, RepositorioRoles repositorioRoles) {
        this.repositorio = repositorio;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioRoles = repositorioRoles;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Paseador registrarPaseador(Paseador nuevoPaseador) {
        EntidadUsuario usuario = repositorioUsuario.findByIdentificacion(nuevoPaseador.getUsuario().getIdentificacion()).orElse(null);
        if (usuario == null)
            throw new ExcepcionFalloEnRegistro(FALLO_EN_REGISTRO);
        Long id = repositorio.obtenerIdPorUsuario(usuario);
        EntidadPaseador entidad = ConvertidorPaseador.convertirAEntidad(nuevoPaseador, id, usuario);
        return ConvertidorPaseador.convertirADominio(repositorio.save(entidad));
    }
}
