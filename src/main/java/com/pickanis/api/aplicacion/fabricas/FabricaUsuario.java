package com.pickanis.api.aplicacion.fabricas;

import com.pickanis.api.aplicacion.comandos.ComandoRegistro;
import com.pickanis.api.dominio.modelo.ContactoUsuario;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaUsuario {

    public Usuario construir(ComandoRegistro registro) {
        ContactoUsuario contacto = ContactoUsuario.crearContacto(registro.getCelular(), registro.getTelefono(), registro.getDireccion());
        return Usuario.nuevoRegistro(registro.getIdentificacion(), registro.getTipoDocumento(), registro.getNombre(), registro.getApellidos(), registro.getCorreo(),
                registro.getNombreUsuario(), registro.getContrasena(), contacto);
    }

    public Paseador construirPaseador(Usuario usuario, ComandoRegistro nuevoRegistro) {
        return new Paseador(usuario, nuevoRegistro.getTiempoExperiencia(), nuevoRegistro.getPerfil());
    }
}
