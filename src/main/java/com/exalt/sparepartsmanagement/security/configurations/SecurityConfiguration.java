package com.exalt.sparepartsmanagement.security.configurations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

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
    @Autowired
    PasswordEncoder passwordEncoder;

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

        clients.inMemory().withClient("my_app").authorizedGrantTypes("password")
                .authorities("all").scopes("all").resourceIds("oauth2-resource").accessTokenValiditySeconds(5000)
                .secret(passwordEncoder.encode("1"));
    }

    /*
    defines the authorization and token endpoints and the token services.
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
