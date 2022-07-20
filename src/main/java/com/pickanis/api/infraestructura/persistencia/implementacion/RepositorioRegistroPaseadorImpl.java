package com.pickanis.api.infraestructura.persistencia.implementacion;

import com.pickanis.api.dominio.excepcion.ExcepcionFalloEnRegistro;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioPaseadorJPA;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RepositorioRegistroPaseadorImpl implements RepositorioRegistroPaseador {

    private static final String FALLO_EN_REGISTRO = "Falló el registro como paseador, no se pudo encontrar el usuario asociado";

    private final RepositorioPaseadorJPA repositorio;
    private final RepositorioRegistroUsuarioJPA repositorioUsuario;

    @Autowired
    public RepositorioRegistroPaseadorImpl(RepositorioPaseadorJPA repositorio, RepositorioRegistroUsuarioJPA repositorioUsuario) {
        this.repositorio = repositorio;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public Paseador registrarPaseador(Paseador nuevoPaseador) {
        EntidadUsuario usuario = repositorioUsuario.findByIdentificacion(nuevoPaseador.getUsuario().getIdentificacion()).orElse(null);
        if (usuario == null)
            throw new ExcepcionFalloEnRegistro(FALLO_EN_REGISTRO);
        EntidadPaseador entidad = ConvertidorPaseador.convertirAEntidad(nuevoPaseador, usuario);
        return ConvertidorPaseador.convertirADominio(repositorio.save(entidad));
    }
}
