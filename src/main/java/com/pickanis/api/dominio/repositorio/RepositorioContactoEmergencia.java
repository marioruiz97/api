package com.pickanis.api.dominio.repositorio;

import com.pickanis.api.dominio.modelo.ContactoEmergencia;

import java.util.List;

public interface RepositorioContactoEmergencia {
    ContactoEmergencia crearOEditarContacto(ContactoEmergencia contacto, String nombreUsuario);

    List<ContactoEmergencia> obtenerMisContactosDeEmergencia(String nombreUsuario);
}
