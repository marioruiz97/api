package com.pickanis.api.dominio.modelo;

import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class Mascota {

    private final Long idMascota;

    @NotBlank
    private final String nombre;

    @NotNull
    private final LocalDate fechaNacimiento;

    @NotNull
    @Min(1)
    private final Integer peso;

    @NotBlank
    private final String raza;

    private final String observaciones;

    @NotBlank
    private final String dueno;

    @NotNull
    private final SexoMascota sexo;

    public Mascota(Long idMascota, String nombre, LocalDate fechaNacimiento, Integer peso, String raza, String observaciones, String dueno, SexoMascota sexo) {
        this.idMascota = idMascota;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.raza = raza;
        this.observaciones = observaciones;
        this.dueno = dueno;
        this.sexo = sexo;
    }
}
