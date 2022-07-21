package com.pickanis.api.infraestructura.persistencia.implementacion;

import com.pickanis.api.dominio.modelo.ContactoEmergencia;
import com.pickanis.api.dominio.repositorio.RepositorioContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioContactoEmergenciaJPA;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroUsuarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RepositorioContactoEmergenciaImpl implements RepositorioContactoEmergencia {

    private static final String USUARIO_NO_ENCONTRADO = "No se encontró el usuario %s en base de datos";
    private final RepositorioContactoEmergenciaJPA repositorioJPA;
    private final RepositorioRegistroUsuarioJPA repositorioUsuarioJPA;

    @Autowired
    public RepositorioContactoEmergenciaImpl(RepositorioContactoEmergenciaJPA repositorioJPA, RepositorioRegistroUsuarioJPA repositorioUsuarioJPA) {
        this.repositorioJPA = repositorioJPA;
        this.repositorioUsuarioJPA = repositorioUsuarioJPA;
    }

    @Override
    public ContactoEmergencia crearOEditarContacto(ContactoEmergencia contacto, String nombreUsuario) {
        EntidadUsuario usuario = repositorioUsuarioJPA.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException(USUARIO_NO_ENCONTRADO));
        EntidadContactoEmergencia nuevoContacto = ConvertidorContactoEmergencia.convertirAEntidad(contacto, usuario);
        return ConvertidorContactoEmergencia.convertirADominio(repositorioJPA.save(nuevoContacto));
    }

    @Override
    public List<ContactoEmergencia> obtenerMisContactosDeEmergencia(String nombreUsuario) {
        EntidadUsuario usuario = repositorioUsuarioJPA.findByNombreUsuario(nombreUsuario)
                .orElseThrow(() -> new UsernameNotFoundException(USUARIO_NO_ENCONTRADO));
        return this.repositorioJPA.findByUsuario(usuario).stream().map(ConvertidorContactoEmergencia::convertirADominio).collect(Collectors.toList());
    }
}
