package com.pickanis.api.infraestructura.persistencia.convertidor;


import com.pickanis.api.dominio.modelo.ContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadContactoEmergencia;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;

public class ConvertidorContactoEmergencia {

    public static EntidadContactoEmergencia convertirAEntidad(ContactoEmergencia contacto, EntidadUsuario usuario) {
        return new EntidadContactoEmergencia(contacto.getId(), usuario, contacto.getNombre(), contacto.getCelular(), contacto.getTelefonoFijo());
    }

    public static ContactoEmergencia convertirADominio(EntidadContactoEmergencia entidad) {
        return new ContactoEmergencia(entidad.getId(), entidad.getNombre(), entidad.getCelular(), entidad.getTelefonoFijo());
    }
}
