package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.TipoDocumento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConvertidorTipoDocumento implements AttributeConverter<TipoDocumento, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoDocumento tipoDocumento) {
        if (tipoDocumento == null) {
            return null;
        }
        return tipoDocumento.getTipoDocumento();
    }

    @Override
    public TipoDocumento convertToEntityAttribute(Integer tipoDocumento) {
        if (tipoDocumento == null) {
            return null;
        }

        return Stream.of(TipoDocumento.values())
                .filter(c -> c.getTipoDocumento().equals(tipoDocumento))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
