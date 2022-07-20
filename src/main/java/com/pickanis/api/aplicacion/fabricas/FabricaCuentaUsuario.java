package com.pickanis.api.aplicacion.fabricas;

import com.pickanis.api.aplicacion.comandos.ComandoInformacionPersonal;
import com.pickanis.api.dominio.modelo.Paseador;
import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaCuentaUsuario {

    public ComandoInformacionPersonal construirPerfilUsuario(Usuario usuario) {
        return new ComandoInformacionPersonal(usuario, false);

    }

    public ComandoInformacionPersonal construirPerfilPaseador(Paseador paseador) {
        return new ComandoInformacionPersonal(paseador.getUsuario(), paseador.getTiempoExperiencia(), paseador.getPerfilExperiencia(), paseador.getEstado(), true);
    }
}
