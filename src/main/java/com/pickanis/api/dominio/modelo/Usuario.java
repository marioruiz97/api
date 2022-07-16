package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.servicios.ValidadorCampoObligatorio;
import lombok.Getter;

@Getter
public class Usuario {

    private final String identificacion;

    private final String nombre;

    private final String apellido;

    private final String correo;

    private final String nombreUsuario;

    private final String contrasena;

    private final String foto;

    private final Boolean habilitado;

    public Usuario(String identificacion, String nombre, String apellido, String correo, String nombreUsuario, String contrasena, String foto, Boolean habilitado) {
        ValidadorCampoObligatorio.validarCampoObligatorio(identificacion, "identificacion");
        ValidadorCampoObligatorio.validarCampoObligatorio(nombre, "nombre");
        ValidadorCampoObligatorio.validarCampoObligatorio(apellido, "apellido");
        ValidadorCampoObligatorio.validarCampoObligatorio(correo, "correo");
        ValidadorCampoObligatorio.validarCampoObligatorio(nombreUsuario, "nombreUsuario");
        ValidadorCampoObligatorio.validarCampoObligatorio(contrasena, "contrasena");

        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.foto = foto;
        this.habilitado = habilitado;
    }

    public static Usuario nuevoRegistro(String identificacion, String nombre, String apellidos, String correo, String usuario, String contrasena) {
        return new Usuario(identificacion, nombre, apellidos, correo, usuario, contrasena, null, true);
    }
}
