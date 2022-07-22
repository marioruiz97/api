package com.pickanis.api.aplicacion.manejadores;

import com.pickanis.api.aplicacion.comandos.ComandoCambioContrasena;
import com.pickanis.api.aplicacion.comandos.ComandoConsultaInformacionPersonal;
import com.pickanis.api.aplicacion.comandos.ComandoGuardarInformacionPersonal;
import com.pickanis.api.aplicacion.fabricas.FabricaCuentaUsuario;
import com.pickanis.api.dominio.excepcion.ExcepcionDatosExpuestos;
import com.pickanis.api.dominio.modelo.ContactoEmergencia;
import com.pickanis.api.dominio.modelo.Usuario;
import com.pickanis.api.dominio.servicios.ServicioCuentaUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorCuentaUsuario {
    private final ServicioCuentaUsuario servicioCuentaUsuario;
    private final FabricaCuentaUsuario fabricaCuentaUsuario;

    @Autowired
    public ManejadorCuentaUsuario(ServicioCuentaUsuario servicioCuentaUsuario, FabricaCuentaUsuario fabricaCuentaUsuario) {
        this.servicioCuentaUsuario = servicioCuentaUsuario;
        this.fabricaCuentaUsuario = fabricaCuentaUsuario;
    }

    public ComandoConsultaInformacionPersonal obtenerMiPerfil(String nombreUsuario) {
        if (this.servicioCuentaUsuario.esPaseador(nombreUsuario)) {
            return this.fabricaCuentaUsuario.construirPerfilPaseador(this.servicioCuentaUsuario.obtenerPerfilPaseador(nombreUsuario));
        } else {
            return this.fabricaCuentaUsuario.construirPerfilUsuario(this.servicioCuentaUsuario.obtenerMiPerfil(nombreUsuario));
        }
    }

    public void guardarMisDatosPersonales(ComandoGuardarInformacionPersonal informacion, String nombreUsuario) {
        if (!nombreUsuario.equals(informacion.getNombreUsuario()))
            throw new ExcepcionDatosExpuestos();
        Usuario usuario = this.fabricaCuentaUsuario.prepararInformacionPersonal(informacion);
        this.servicioCuentaUsuario.guardarInformacionPersonal(usuario, nombreUsuario);
    }

    public ContactoEmergencia agregarContactoEmergencia(ContactoEmergencia contacto, String nombreUsuario) {
        return this.servicioCuentaUsuario.crearOEditarContactoEmergencia(contacto, nombreUsuario);
    }

    public List<ContactoEmergencia> obtenerMisContactosDeEmergencia(String nombreUsuario) {
        return this.servicioCuentaUsuario.obtenerMisContactosDeEmergencia(nombreUsuario);
    }

    public void desactivarCuenta(String nombreUsuario, String identificacion) {
        Usuario usuario = obtenerMiPerfil(nombreUsuario).getUsuario();
        if (!identificacion.equals(usuario.getIdentificacion()))
            throw new UsernameNotFoundException("No se encontró el usuario en base de datos");
        this.servicioCuentaUsuario.desactivarCuenta(usuario);
    }

    public void cambiarContrasena(String nombreUsuario, ComandoCambioContrasena nuevaContrasena) {
        this.servicioCuentaUsuario.cambiarContrasena(nombreUsuario, nuevaContrasena.getContrasena());
    }
}
