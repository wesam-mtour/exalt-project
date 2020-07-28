package com.exalt.sparepartsmanagement.security;

import com.exalt.sparepartsmanagement.security.service.UserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
The @EnableWebSecurity annotation is important if we disable the default security configuration.
the application will fail to start. The annotation is only optional if we're just overriding
the default behavior using a WebSecurityConfigurerAdapter.
 */
@EnableOAuth2Sso
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*
    Authentication
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(getPasswordEncoding());
//        return authProvider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoding() {
//        return NoOpPasswordEncoder.getInstance();
//    }
    /*
    Authorization
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and()
//                .authorizeRequests()
//                .antMatchers("/**").hasRole("A");
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().antMatcher("/**").authorizeRequests().antMatchers("/", "/login**").permitAll()
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/").permitAll();
    }
}
