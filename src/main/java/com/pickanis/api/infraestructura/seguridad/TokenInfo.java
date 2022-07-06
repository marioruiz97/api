package com.pickanis.api.infraestructura.seguridad;

import com.pickanis.api.infraestructura.persistencia.entidad.EntidadUsuario;
import com.pickanis.api.infraestructura.persistencia.repositorio.RepositorioAutenticacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TokenInfo implements TokenEnhancer {
    private static final String EMAIL_KEY = "usuario_email";
    private static final String ENABLED_KEY = "usuario_enabled";
    private static final String USER_ID_KEY = "usuario_id";
    private static final String USERNAME_KEY = "usuario_name";
    private static final String ROLES_KEY = "usuario_roles";

    @Autowired
    private RepositorioAutenticacion userAuthService;


    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> tokenInfo = new HashMap<>();
        EntidadUsuario usuario = userAuthService.findByNombreUsuario(authentication.getName()).orElse(null);
        if (usuario == null) {
            String message = "Usuario no encontrado";
            throw new UsernameNotFoundException(message);
        }
        tokenInfo.put(EMAIL_KEY, usuario.getCorreo());
        tokenInfo.put(USERNAME_KEY, usuario.getNombre().concat(" ").concat(usuario.getApellido()));
        tokenInfo.put(ENABLED_KEY, usuario.getHabilitado());
        tokenInfo.put(USER_ID_KEY, usuario.getIdentificacion());
        tokenInfo.put(ROLES_KEY, authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(tokenInfo);
        return accessToken;
    }
}
