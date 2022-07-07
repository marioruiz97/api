package com.pickanis.api.aplicacion.fabricas;

import com.pickanis.api.aplicacion.comandos.ComandoRegistro;
import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class FabricaUsuario {

    public Usuario construir(ComandoRegistro comandoRegistro) {
        return new Usuario();
    }
}
