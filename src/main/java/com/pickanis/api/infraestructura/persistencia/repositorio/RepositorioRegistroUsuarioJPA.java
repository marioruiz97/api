package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRegistroUsuarioJPA extends JpaRepository<EntidadUsuario, String>, RepositorioRegistroUsuario {

    @Override
    default Usuario registrarUsuario(Usuario usuario) {
        return usuario;
    }

    @Override
    default Usuario buscarUsuarioPorCorreo(String correo) {
        return null;
    }

    @Override
    default Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        return null;
    }

    @Override
    default Usuario buscarUsuarioPorIdentificacion(String identificacion) {
        return null;
    }

}
