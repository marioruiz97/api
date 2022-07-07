package com.pickanis.api.infraestructura.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;
import java.util.List;

@PropertySource(value = "classpath:security.properties")
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${security.rsa.secret.key}")
    private String LLAVE_SECRETA;

    @Value("${security.rsa.public.key}")
    private String LLAVE_PUBLICA;

    @Value("${security.client.app.password}")
    private String CLIENTAPP_PASSWORD;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenInfo tokenEnhancer;

    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("front-pickanis").secret(passwordEncoder.encode(CLIENTAPP_PASSWORD))
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(5000);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = Arrays.asList(tokenEnhancer, accessTokenConverter());
        enhancerChain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .tokenEnhancer(enhancerChain);
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(LLAVE_SECRETA);
        accessTokenConverter.setVerifierKey(LLAVE_PUBLICA);
        return accessTokenConverter;
    }

}
