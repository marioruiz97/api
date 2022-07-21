package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.servicios.ValidadorCampoObligatorio;
import lombok.Getter;

@Getter
public class Usuario {

    private final String identificacion;

    private final TipoDocumento tipoDocumento;

    private final String nombre;

    private final String apellido;

    private final String correo;

    private final ContactoUsuario contacto;

    private final String nombreUsuario;

    private final String contrasena;

    private final String foto;

    private final Boolean habilitado;

    public Usuario(String identificacion, Integer tipoDocumento, String nombre, String apellido, String correo, String nombreUsuario, String contrasena, String foto, Boolean habilitado, ContactoUsuario contacto) {
        ValidadorCampoObligatorio.validarCampoObligatorio(identificacion, "identificacion");
        ValidadorCampoObligatorio.validarCampoObligatorio(nombre, "nombre");
        ValidadorCampoObligatorio.validarCampoObligatorio(apellido, "apellido");
        ValidadorCampoObligatorio.validarCampoObligatorio(correo, "correo");
        ValidadorCampoObligatorio.validarCampoObligatorio(nombreUsuario, "nombreUsuario");
        ValidadorCampoObligatorio.validarCampoObligatorio(contrasena, "contrasena");
        ValidadorCampoObligatorio.validarCampoObligatorio(tipoDocumento, "tipo de documento");

        this.identificacion = identificacion;
        this.tipoDocumento = TipoDocumento.buildEnum(tipoDocumento);
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.foto = foto;
        this.habilitado = habilitado;
        this.contacto = contacto;
    }

    public static Usuario nuevoRegistro(String identificacion, Integer tipoDocumento, String nombre, String apellidos, String correo, String usuario, String contrasena, ContactoUsuario contacto) {
        return new Usuario(identificacion, tipoDocumento, nombre, apellidos, correo, usuario, contrasena, null, true, contacto);
    }

    public static Usuario actualizarDatos(Usuario nuevo, Usuario viejo) {
        ContactoUsuario nuevoContacto = nuevo.getContacto();
        ContactoUsuario contacto = new ContactoUsuario(viejo.getContacto().getId(), nuevoContacto.getCelular(), nuevoContacto.getTelefonoFijo(), nuevoContacto.getDireccion());
        return new Usuario(viejo.getIdentificacion(),
                viejo.getTipoDocumento().getTipoDocumento(),
                nuevo.getNombre(),
                nuevo.getApellido(),
                nuevo.getCorreo(),
                viejo.getNombreUsuario(),
                viejo.getContrasena(),
                viejo.getFoto(),
                viejo.getHabilitado(),
                contacto);
    }
}
