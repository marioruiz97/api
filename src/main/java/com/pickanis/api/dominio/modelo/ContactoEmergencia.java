package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.servicios.ValidadorCampoObligatorio;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class ContactoEmergencia {

    @NotBlank
    private final String nombre;
    @NotBlank
    @Pattern(regexp = "(^$|[0-9]{7,12})")
    private final String celular;

    @Pattern(regexp = "(^$|[0-9]{7,12})")
    private final String telefonoFijo;
    @Setter
    private Integer id;

    public ContactoEmergencia(Integer id, String nombre, String celular, String telefonoFijo) {
        ValidadorCampoObligatorio.validarCampoObligatorio(nombre, "nombre");
        ValidadorCampoObligatorio.validarCampoObligatorio(celular, "celular");

        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.telefonoFijo = telefonoFijo;
    }
}
