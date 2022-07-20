package com.pickanis.api.infraestructura.controladores;


import com.pickanis.api.dominio.excepcion.ExcepcionDatosEntrada;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class ControladorBase {

    public void validarDatosEntrada(BindingResult result) {
        if (result.hasErrors()) {
            List<String> errores = result.getFieldErrors().stream().map(err -> {
                String field = err.getField().split(" ") + ": ";
                return field + err.getDefaultMessage();
            }).collect(Collectors.toList());
            ExcepcionDatosEntrada excepcion = new ExcepcionDatosEntrada(errores);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de entrada no válidos", excepcion);
        }
    }

    public String obtenerUsuarioEnSesion() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
