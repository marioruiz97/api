package com.pickanis.api.aplicacion.fabricas;

import com.pickanis.api.aplicacion.comandos.ComandoRegistro;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaUsuario {

    public Usuario construir(ComandoRegistro registro) {
        return Usuario.nuevoRegistro(registro.getIdentificacion(), registro.getNombre(), registro.getApellidos(), registro.getCorreo(),
                registro.getUsuario(), registro.getContrasena());
    }

    public Paseador construirPaseador(Usuario usuario, ComandoRegistro nuevoRegistro) {
        return new Paseador(usuario, nuevoRegistro.getTiempoExperiencia(), nuevoRegistro.getPerfil(), nuevoRegistro.getEstado());
    }
}
