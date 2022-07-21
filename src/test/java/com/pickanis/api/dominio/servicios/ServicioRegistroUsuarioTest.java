package com.pickanis.api.dominio.servicios;

import com.pickanis.api.dominio.excepcion.ExcepcionUsuarioRegistrado;
import com.pickanis.api.dominio.modelo.ContactoUsuario;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroPaseador;
import com.pickanis.api.dominio.repositorio.RepositorioRegistroUsuario;
import com.pickanis.api.dominio.repositorio.RepositorioRoles;
import com.pickanis.api.infraestructura.persistencia.entidad.Roles;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioRegistroUsuarioJPA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioRegistroUsuarioTest {

    RepositorioRegistroUsuario repoUsuario = Mockito.mock(RepositorioRegistroUsuarioJPA.class);
    RepositorioRegistroPaseador repoPaseador = Mockito.mock(RepositorioRegistroPaseador.class);
    RepositorioRoles repoRoles = Mockito.mock(RepositorioRoles.class);
    ServicioRegistroUsuario servicio = new ServicioRegistroUsuario(repoUsuario, repoPaseador, repoRoles);

    @Test
    void registrarUsuario() {
        Usuario prueba = crearUsuarioPrueba();
        Roles rol = new Roles(1L, "ROLE_USUARIO");
        Mockito.when(repoRoles.obtenerRolUsuario()).thenReturn(rol);
        Mockito.when(repoUsuario.registrarUsuario(prueba, rol)).thenReturn(prueba);
        Usuario result = servicio.registrarUsuario(prueba);

        Assertions.assertEquals(prueba, result);
    }

    @Test
    void registrarUsuarioFallaPorCorreo() {
        Usuario prueba = crearUsuarioPrueba();
        Mockito.when(repoUsuario.buscarUsuarioPorCorreo(prueba.getCorreo())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }

    @Test
    void registrarUsuarioFallaPorUsername() {
        Usuario prueba = crearUsuarioPrueba();
        Mockito.when(repoUsuario.buscarUsuarioPorNombreUsuario(prueba.getNombreUsuario())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }

    @Test
    void registrarUsuarioFallaPorIdentificacion() {
        Usuario prueba = crearUsuarioPrueba();
        Mockito.when(repoUsuario.buscarUsuarioPorIdentificacion(prueba.getIdentificacion())).thenReturn(prueba);
        org.assertj.core.api.Assertions.assertThatExceptionOfType(ExcepcionUsuarioRegistrado.class).isThrownBy(() -> servicio.registrarUsuario(prueba));
    }

    private Usuario crearUsuarioPrueba() {
        ContactoUsuario contacto = ContactoUsuario.crearContacto("301456677", "454545454", "clle falsa 123 # 2");
        return new Usuario("1234567", 1, "mario", "ruiz", "mario@hotmail.com", "maruiz", "1234546", null, true, contacto);
    }
}
