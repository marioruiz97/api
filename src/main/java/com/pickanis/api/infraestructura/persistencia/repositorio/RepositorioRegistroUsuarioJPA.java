package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RepositorioRegistroUsuarioJPA extends JpaRepository<EntidadUsuario, String>, RepositorioRegistroUsuario {

    @Transactional
    @Override
    default Usuario registrarUsuario(Usuario usuario, Roles rolUsuario) {
        EntidadUsuario entidad = ConvertidorUsuario.convertirAEntidad(usuario, rolUsuario);
        return ConvertidorUsuario.convertirADominio(save(entidad));
    }

    @Override
    default Usuario buscarUsuarioPorCorreo(String correo) {
        EntidadUsuario entidad = findByCorreo(correo).orElse(null);
        if (entidad != null)
            return ConvertidorUsuario.convertirADominio(entidad);
        else
            return null;
    }

    @Override
    default Usuario buscarUsuarioPorNombreUsuario(String nombreUsuario) {
        EntidadUsuario entidad = findByNombreUsuario(nombreUsuario).orElse(null);
        if (entidad != null)
            return ConvertidorUsuario.convertirADominio(entidad);
        else
            return null;
    }

    @Override
    default Usuario buscarUsuarioPorIdentificacion(String identificacion) {
        EntidadUsuario entidad = findByIdentificacion(identificacion).orElse(null);
        if (entidad != null)
            return ConvertidorUsuario.convertirADominio(entidad);
        else
            return null;
    }

    @Transactional
    @Override
    default Usuario registrarUsuarioPaseador(Usuario usuario, Roles rolPaseador) {
        EntidadUsuario entidad = ConvertidorUsuario.convertirAEntidad(usuario, rolPaseador);
        return ConvertidorUsuario.convertirADominio(save(entidad));
    }

    // métodos JPQL

    /**
     * métodos JPQL, por favor no usar directamente a menos que sea estrictamente necesario.
     * en lo posible usar las implementaciones descritas arribas.
     * Usar {@link RepositorioRegistroUsuarioJPA#buscarUsuarioPorCorreo(String)}
     */
    Optional<EntidadUsuario> findByCorreo(String correo);

    /**
     * métodos JPQL, por favor no usar directamente a menos que sea estrictamente necesario.
     * en lo posible usar las implementaciones descritas arribas.
     * Usar {@link RepositorioRegistroUsuarioJPA#buscarUsuarioPorNombreUsuario(String)}
     */
    Optional<EntidadUsuario> findByNombreUsuario(String nombreUsuario);

    /**
     * métodos JPQL, por favor no usar directamente a menos que sea estrictamente necesario.
     * en lo posible usar las implementaciones descritas arribas.
     * Usar {@link RepositorioRegistroUsuarioJPA#buscarUsuarioPorIdentificacion(String)}
     */
    Optional<EntidadUsuario> findByIdentificacion(String identificacion);
}
