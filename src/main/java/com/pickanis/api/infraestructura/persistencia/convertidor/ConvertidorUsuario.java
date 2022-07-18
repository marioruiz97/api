package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import com.pickanis.api.infraestructura.seguridad.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class ConvertidorUsuario {

    private static UserDetailsServiceImpl servicioUsuario;

    @Autowired
    public ConvertidorUsuario(UserDetailsServiceImpl servicioUsuario) {
        ConvertidorUsuario.servicioUsuario = servicioUsuario;
    }

    public static Usuario convertirADominio(EntidadUsuario entidad) {
        Usuario dominio = null;
        if (entidad != null) {
            String fakePassword = "111";
            dominio = new Usuario(entidad.getIdentificacion(), entidad.getNombre(), entidad.getApellido(), entidad.getCorreo(), entidad.getNombreUsuario(), fakePassword, entidad.getFoto(), entidad.getHabilitado());
        }
        return dominio;
    }

    public static EntidadUsuario convertirAEntidad(Usuario dominio, Roles rolUsuario) {
        String contrasena = servicioUsuario.codificarContrasena(dominio.getContrasena());
        return new EntidadUsuario(dominio.getIdentificacion(), dominio.getNombre(), dominio.getApellido(), dominio.getCorreo(), dominio.getNombreUsuario(), contrasena, dominio.getFoto(), dominio.getHabilitado(), Arrays.asList(rolUsuario));
    }
}
