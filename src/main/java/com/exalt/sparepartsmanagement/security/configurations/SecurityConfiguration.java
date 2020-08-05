package com.exalt.sparepartsmanagement.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/*
 *The @EnableWebSecurity annotation is important if we disable the default security configuration.
    the application will fail to start. The annotation is only optional if we're just overriding
    the default behavior using a WebSecurityConfigurerAdapter.

 *AuthorizationServerConfigurerAdapter is used to configure how the OAuth authorization server works.

 *WebSecurityConfigurerAdapter is used to configure how the OAuth authorization server is secured.
    Or in other words, how the user has to authenticate to grant a client access to his resources.
 */
@Configuration
@EnableAuthorizationServer
public class SecurityConfiguration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;


    /*
    defines the security constraints on the token endpoint
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         endpoints are not exposed by default (have access "denyAll()")
         checkTokenAccess isAuthenticated or not
         */
        security.checkTokenAccess("isAuthenticated()");
    }

    /*
    configurer that defines the client details service. Client details can be initialized,
    or you can just refer to an existing store
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient("qq")
                .secret("1")
                .authorities("ROLE_A")
                .scopes("all")
                .authorizedGrantTypes("client_credentials");
    }

    /*
    defines the authorization and token endpoints and the token services.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
