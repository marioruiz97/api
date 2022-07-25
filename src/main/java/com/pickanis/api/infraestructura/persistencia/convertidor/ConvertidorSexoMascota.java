package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.SexoMascota;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConvertidorSexoMascota implements AttributeConverter<SexoMascota, String> {

    @Override
    public String convertToDatabaseColumn(SexoMascota sexoMascota) {
        if (sexoMascota == null) return null;
        return sexoMascota.getSexo();
    }

    @Override
    public SexoMascota convertToEntityAttribute(String codigoSexo) {
        if (codigoSexo == null) return null;

        return Stream.of(SexoMascota.values())
                .filter(c -> c.getSexo().equals(codigoSexo))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
