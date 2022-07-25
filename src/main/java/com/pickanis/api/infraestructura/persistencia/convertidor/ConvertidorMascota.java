package com.pickanis.api.infraestructura.persistencia.convertidor;

import com.pickanis.api.dominio.modelo.Mascota;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadMascota;
import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;

public class ConvertidorMascota {

    public static EntidadMascota convertirAEntidad(Mascota mascota, EntidadUsuario dueno) {
        return new EntidadMascota(
                mascota.getIdMascota(),
                mascota.getNombre(),
                mascota.getRaza(),
                mascota.getSexo(),
                mascota.getFechaNacimiento(),
                dueno,
                mascota.getPeso(),
                mascota.getObservaciones(),
                "");
    }

    public static Mascota convertirADominio(EntidadMascota mascota) {
        return new Mascota(
                mascota.getIdMascota(),
                mascota.getNombre(),
                mascota.getFechaNacimiento(),
                mascota.getPeso(),
                mascota.getRaza(),
                mascota.getObservaciones(),
                mascota.getDueno().getIdentificacion(),
                mascota.getSexo());
    }
}
