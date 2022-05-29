package com.pickanis.api.infraestructura.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rutas")
public class EntidadRuta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRuta;

    @ManyToOne
    @JoinColumn(name = "identificacion_paseador", referencedColumnName = "idPaseador", nullable = false)
    private EntidadPaseador idPaseador;

    @NotBlank(message = "El campo Fecha Ruta no puede estar vacío")
    @Column( nullable = false)
    private LocalDate fechaRuta;

    @NotBlank(message = "El campo Hora Salida Ruta no puede estar vacío")
    @Column( nullable = false)
    private LocalTime horaSalida;

    @NotBlank(message = "El campo Punto Salida no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String puntoSalida;

    @NotBlank(message = "El campo Punto Llegada no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String puntoLlegada;

    @NotBlank(message = "El campo Descripcion no puede estar vacío")
    @Column(length = 50, nullable = false)
    private String descripcionRecorrido;

    @NotBlank(message = "El campo Estado no puede estar vacío")
    @Column(length = 1, nullable = false)
    private String estado;

    @Column(length = 100) //Dirreccion a la ubicacionde la imagen
    private String foto;
}
