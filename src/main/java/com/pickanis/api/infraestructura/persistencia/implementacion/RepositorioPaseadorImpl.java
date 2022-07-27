package com.pickanis.api.infraestructura.persistencia.implementacion;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioPaseador;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioPaseadorJPA;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioPaseadorImpl implements RepositorioPaseador {

    private final RepositorioPaseadorJPA repositorioPaseadorJPA;
    private final RepositorioUsuarioJPA repositorioUsuarioJPA;


    @Autowired
    public RepositorioPaseadorImpl(RepositorioPaseadorJPA repositorioPaseadorJPA, RepositorioUsuarioJPA repositorioUsuarioJPA) {
        this.repositorioPaseadorJPA = repositorioPaseadorJPA;
        this.repositorioUsuarioJPA = repositorioUsuarioJPA;
    }

    @Override
    public List<Paseador> obtenerPaseadores() {
        return this.repositorioPaseadorJPA.findAll().stream().map(ConvertidorPaseador::convertirADominio).collect(Collectors.toList());
    }

    @Override
    public Paseador obtenerPaseadorPorIdentificacion(String identificacion) {
        return ConvertidorPaseador.convertirADominio(this.repositorioPaseadorJPA.findByIdPaseadorIgnoreCase(identificacion).orElse(null));
    }

    @Override
    public void desactivarPaseador(String identificacion) {
        EntidadPaseador paseador = this.repositorioPaseadorJPA.findByIdPaseadorIgnoreCase(identificacion).orElse(null);
        if (paseador != null) {
            paseador.desactivarPaseador();
            this.repositorioPaseadorJPA.saveAndFlush(paseador);
        }
    }

    @Override
    public void guardarPerfilPaseador(Usuario usuario, String perfil, String tiempoExperiencia) {
        EntidadPaseador paseador = this.repositorioPaseadorJPA.findByIdPaseadorIgnoreCase(usuario.getIdentificacion()).orElse(null);
        if (paseador != null) {
            paseador.editarInfoPerfilActivo(perfil, tiempoExperiencia);
            this.repositorioPaseadorJPA.saveAndFlush(paseador);
        } else {
            EntidadUsuario entidadUsuario = repositorioUsuarioJPA.getById(usuario.getIdentificacion());
            EntidadPaseador nuevoPaseador = new EntidadPaseador(usuario.getIdentificacion(), entidadUsuario, tiempoExperiencia, perfil, true, null);
            this.repositorioPaseadorJPA.saveAndFlush(nuevoPaseador);
        }
    }

    @Override
    public boolean esPaseador(String identificacion) {
        return this.repositorioPaseadorJPA.existsByIdPaseadorEqualsIgnoreCase(identificacion);
    }

}
