package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.servicios.ValidadorCampoObligatorio;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ContactoUsuario {

    private final String celular;
    private final String telefonoFijo;
    private final String direccion;

    @Setter
    private Integer id;

    public ContactoUsuario(Integer id, String celular, String telefonoFijo, String direccion) {
        ValidadorCampoObligatorio.validarCampoObligatorio(celular, "celular");
        ValidadorCampoObligatorio.validarCampoObligatorio(telefonoFijo, "teléfono");
        ValidadorCampoObligatorio.validarCampoObligatorio(direccion, "dirección");

        this.id = id;
        this.celular = celular;
        this.telefonoFijo = telefonoFijo;
        this.direccion = direccion;
    }

    public static ContactoUsuario crearContacto(String celular, String telefono, String direccion) {
        return new ContactoUsuario(null, celular, telefono, direccion);
    }

}
