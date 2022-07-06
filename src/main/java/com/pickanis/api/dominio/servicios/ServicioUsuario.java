package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.modelo.Usuario;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuario {
    public void registrarUsuario(Usuario construir) {
        System.out.println("se ha registrado un usuario");
    }
}
