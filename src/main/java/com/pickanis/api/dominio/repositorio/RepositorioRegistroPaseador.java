package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadPaseador;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;

import java.util.Optional;

public interface RepositorioRegistroPaseador {

    Paseador registrarPaseador(Paseador nuevoPaseador);

    default Long obtenerIdPorUsuario(EntidadUsuario usuario) {
        EntidadPaseador paseador = findByUsuario(usuario).orElse(null);
        return paseador != null ? paseador.getIdPaseador() : null;
    }

    Optional<EntidadPaseador> findByUsuario(EntidadUsuario usuario);
}
