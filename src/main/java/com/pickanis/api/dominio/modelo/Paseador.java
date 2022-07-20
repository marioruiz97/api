package com.pickanis.api.dominio.modelo;

import com.pickanis.api.dominio.excepcion.ExcepcionCampoObligatorio;
import lombok.Getter;

@Getter
public class Paseador {

    private static final String ERROR_TIEMPO_EXPERIENCIA = "El tiempo de experiencia es obligatorio";
    private static final String ERROR_PERFIL_EXPERIENCIA = "El perfil del paseador es obligatorio";
    private final Usuario usuario;
    private final String tiempoExperiencia;
    private final String perfilExperiencia;
    private Boolean estado;
    private Integer calificacion;

    public Paseador(Usuario usuario, String tiempoExperiencia, String perfilExperiencia) {
        validarDatos(tiempoExperiencia, perfilExperiencia);
        this.usuario = usuario;
        this.tiempoExperiencia = tiempoExperiencia;
        this.perfilExperiencia = perfilExperiencia;
    }

    public Paseador(Usuario usuario, String tiempoExperiencia, String perfilExperiencia, Boolean estado) {
        validarDatos(tiempoExperiencia, perfilExperiencia);
        this.usuario = usuario;
        this.tiempoExperiencia = tiempoExperiencia;
        this.perfilExperiencia = perfilExperiencia;
        this.estado = estado;
    }

    public Paseador(Usuario usuario, String tiempoExperiencia, String perfilExperiencia, Boolean estado, Integer calificacion) {
        validarDatos(tiempoExperiencia, perfilExperiencia);
        this.usuario = usuario;
        this.tiempoExperiencia = tiempoExperiencia;
        this.perfilExperiencia = perfilExperiencia;
        this.estado = estado;
        this.calificacion = calificacion;
    }

    private void validarDatos(String tiempoExperiencia, String perfilExperiencia) {
        if (tiempoExperiencia.equals(null) || tiempoExperiencia.equals("")) {
            throw new ExcepcionCampoObligatorio(ERROR_TIEMPO_EXPERIENCIA);
        }
        if (perfilExperiencia.equals(null) || perfilExperiencia.equals("")) {
            throw new ExcepcionCampoObligatorio(ERROR_PERFIL_EXPERIENCIA);
        }
    }

    public void activarPaseador() {
        this.estado = true;
    }

    public void desactivarPaseador() {
        this.estado = false;
    }
}
