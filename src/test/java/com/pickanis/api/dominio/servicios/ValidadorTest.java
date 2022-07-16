package com.pickanis.api.dominio.servicios;


import com.pickanis.api.dominio.excepcion.ExcepcionCampoObligatorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorTest {

    @Test
    void validarCampoObligatorioSuccess() {
        String datoString = "prueba";
        Integer datoInteger = 123;

        Assertions.assertThatNoException().isThrownBy(() -> ValidadorCampoObligatorio.validarCampoObligatorio(datoString, "prueba String"));
        Assertions.assertThatNoException().isThrownBy(() -> ValidadorCampoObligatorio.validarCampoObligatorio(datoInteger, "Prueba integer"));
    }

    @Test
    void validarCampoObligatorioStringFail() {
        String datoString = "";
        String campo = "prueba string";
        String mensajeEsperado = String.format("El valor ingresado para el campo %s no corresponde al tipo de dato esperado", campo);
        Assertions.assertThatExceptionOfType(ExcepcionCampoObligatorio.class)
                .isThrownBy(() -> ValidadorCampoObligatorio.validarCampoObligatorio(datoString, campo))
                .withMessage(mensajeEsperado);
    }

    @Test
    void validarCampoObligatorioIntegerFail() {
        Integer datoInteger = null;
        String campo = "prueba integer";
        String mensajeEsperado = String.format("El valor ingresado para el campo %s no corresponde al tipo de dato esperado", campo);
        Assertions.assertThatExceptionOfType(ExcepcionCampoObligatorio.class)
                .isThrownBy(() -> ValidadorCampoObligatorio.validarCampoObligatorio(datoInteger, campo))
                .withMessage(mensajeEsperado);
    }

}
