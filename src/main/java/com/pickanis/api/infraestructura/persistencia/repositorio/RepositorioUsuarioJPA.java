package com.pickanis.api.infraestructura.persistencia.repositorio;

import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioCuentaUsuario;
import com.pickanis.api.infraestructura.persistencia.convertidor.ConvertidorUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositorioUsuarioJPA extends RepositorioCuentaUsuario, JpaRepository<EntidadUsuario, String> {

    @Override
    @Query(value = "select u.identificacion from usuarios u where u.nombre_usuario= :nombreUsuario", nativeQuery = true)
    String obtenerIdentificacion(@Param("nombreUsuario") String nombreUsuario);

    @Override
    default Usuario obtenerUsuarioPorIdentificacion(String identificacion) {
        return ConvertidorUsuario.convertirADominio(findByIdentificacionIgnoreCase(identificacion).orElse(null));
    }

    @Override
    default Usuario obtenerUsuarioPorNombreUsuario(String nombreUsuario) {
        return ConvertidorUsuario.convertirADominio(findByNombreUsuarioIgnoreCase(nombreUsuario).orElse(null));
    }

    @Override
    default void guardarInformacionPersonal(Usuario usuario) {
        EntidadUsuario entidad = findByIdentificacionIgnoreCase(usuario.getIdentificacion())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No se encontró el usuario %s en base de datos", usuario.getNombreUsuario())));
        entidad.actualizarInformacionPersonal(usuario);
        EntidadUsuario entidadGuardada = save(entidad);
        System.out.printf("Se ha actualizado la informacion personal de %s", entidadGuardada.getNombreUsuario());
    }

    @Override
    default void cambiarContrasena(String nombreUsuario, String nuevaContrasena) {
        Usuario usuario = obtenerUsuarioPorNombreUsuario(nombreUsuario);
        if(usuario==null) throw new UsernameNotFoundException(String.format("No se encontró el usuario %s en base de datos", nombreUsuario));
        Usuario conNuevaContrasena = Usuario.cambiarContrasena(usuario,nuevaContrasena);
        EntidadUsuario entidad = findByNombreUsuarioIgnoreCase(nombreUsuario).orElse(null);
        Roles roles =  entidad == null ? new Roles(1L, "ROLE_USUARIO") : entidad.getRoles().get(0);
        save(ConvertidorUsuario.convertirAEntidad(conNuevaContrasena, roles));
    }

    @Override
    default void desactivarUsuario(Usuario usuario) {
        EntidadUsuario entidad = findByIdentificacionIgnoreCase(usuario.getIdentificacion())
                .orElseThrow(() -> new UsernameNotFoundException(String.format("No se encontró el usuario %s en base de datos", usuario.getNombreUsuario())));
        entidad.desactivarCuenta();
        save(entidad);
    }

    Optional<EntidadUsuario> findByIdentificacionIgnoreCase(String identificacion);

    Optional<EntidadUsuario> findByNombreUsuarioIgnoreCase(String nombreUsuario);
}
