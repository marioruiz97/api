package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;

public final class ConvertidorPaseador {

    public static EntidadPaseador convertirAEntidad(Paseador dominio, Long id, EntidadUsuario usuario) {
        return new EntidadPaseador(id, usuario, dominio.getTiempoExperiencia(), dominio.getPerfilExperiencia(), dominio.getEstado(), dominio.getCalificacion());
    }

    public static Paseador convertirADominio(EntidadPaseador entidad) {
        Paseador paseador = null;
        if (entidad != null) {
            Usuario usuario = ConvertidorUsuario.convertirADominio(entidad.getUsuario());
            paseador = new Paseador(usuario, entidad.getTiempoExperiencia(), entidad.getPerfilExperiencia(), entidad.getEstado(), entidad.getCalificacion());
        }
        return paseador;
    }

}