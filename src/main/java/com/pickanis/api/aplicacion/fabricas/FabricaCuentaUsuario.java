package com.pickanis.api.aplicacion.fabricas;

import com.pickanis.api.aplicacion.comandos.ComandoConsultaInformacionPersonal;
import com.pickanis.api.aplicacion.comandos.ComandoGuardarInformacionPersonal;
import com.pickanis.api.dominio.modelo.ContactoUsuario;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaCuentaUsuario {

    public ComandoConsultaInformacionPersonal construirPerfilUsuario(Usuario usuario) {
        return new ComandoConsultaInformacionPersonal(usuario, false);

    }

    public ComandoConsultaInformacionPersonal construirPerfilPaseador(Paseador paseador) {
        return new ComandoConsultaInformacionPersonal(paseador.getUsuario(), paseador.getTiempoExperiencia(), paseador.getPerfilExperiencia(), paseador.getEstado(), true);
    }

    public Usuario prepararInformacionPersonal(ComandoGuardarInformacionPersonal informacion) {
        ContactoUsuario contacto = new ContactoUsuario(null, informacion.getCelular(), informacion.getTelefonoFijo(), informacion.getDireccion());
        String fakePassword = "1111";
        return new Usuario(
                informacion.getIdentificacion(),
                informacion.getTipoDocumento(),
                informacion.getNombre(),
                informacion.getApellido(),
                informacion.getCorreo(),
                informacion.getNombreUsuario(),
                fakePassword,
                null,
                true,
                contacto);
    }
}
