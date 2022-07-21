package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.ContactoUsuario;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadContactoUsuario;

public class ConvertidorContactoUsuario {
    public static EntidadContactoUsuario convertirAEntidad(ContactoUsuario contacto) {
        return new EntidadContactoUsuario(contacto.getId(), contacto.getCelular(), contacto.getTelefonoFijo(), contacto.getDireccion());
    }

    public static ContactoUsuario convertirADominio(EntidadContactoUsuario entidad) {
        return new ContactoUsuario(entidad.getIdContactoUsuario(), entidad.getCelular(), entidad.getTelefonoFijo(), entidad.getDireccion());
    }
}
