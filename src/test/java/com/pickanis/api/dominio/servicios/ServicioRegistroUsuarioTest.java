package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionUsuarioRegistrado;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroUsuarioJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioRegistroUsuarioTest {

    RepositorioRegistroUsuario repoUsuario = Mockito.mock(RepositorioRegistroUsuarioJPA.class);
    RepositorioRegistroPaseador repoPaseador = Mockito.mock(RepositorioRegistroPaseador.class);
    ServicioRegistroUsuario servicio = new ServicioRegistroUsuario(repoUsuario, repoPaseador);

    @Test
    void registrarUsuario() {
        Usuario prueba = new Usuario("1234567", "mario", "ruiz", "mario@hotmail.com", "maruiz", "1234546", null, true);
        Mockito.when(repoUsuario.registrarUsuario(prueba)).thenReturn(prueba);
        Usuario result = servicio.registrarUsuario(prueba);

        Assertions.assertEquals(prueba, result);
    }

    @Test
    void registrarUsuarioFallaPorCorreo() {
        Usuario prueba = new Usuario("1234567", "mario", "ruiz", "mario@hotmail.com", "maruiz", "1234546", null, true);
        Mockito.when(repoUsuario.buscarUsuarioPorCorreo(prueba.getCorreo())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }

    @Test
    void registrarUsuarioFallaPorUsername() {
        Usuario prueba = new Usuario("1234567", "mario", "ruiz", "mario@hotmail.com", "maruiz", "1234546", null, true);
        Mockito.when(repoUsuario.buscarUsuarioPorNombreUsuario(prueba.getNombreUsuario())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }

    @Test
    void registrarUsuarioFallaPorIdentificacion() {
        Usuario prueba = new Usuario("1234567", "mario", "ruiz", "mario@hotmail.com", "maruiz", "1234546", null, true);
        Mockito.when(repoUsuario.buscarUsuarioPorIdentificacion(prueba.getIdentificacion())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }
}
