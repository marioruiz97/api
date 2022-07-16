package com.pickanis.api.infraestructura.seguridad;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final RepositorioAutenticacion repositorioUsuario;

    @Autowired
    public UserDetailsServiceImpl(RepositorioAutenticacion repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        EntidadUsuario usuario = repositorioUsuario.findByCorreoIgnoreCase(usernameOrEmail)
                .orElse(repositorioUsuario.findByCorreoIgnoreCase(usernameOrEmail)
                        .orElse(null));
        if (usuario == null) {
            String message = "usuario no encontrado";
            throw new UsernameNotFoundException(message);
        }
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombreRol())).collect(Collectors.toList());

        // TODO: añadir logica para cuenta verificada y cuenta bloqueada y cambiar los valores quemados
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        return new User(usuario.getNombreUsuario(), usuario.getContrasena(), usuario.getHabilitado(), accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
