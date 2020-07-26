package com.exalt.sparepartsmanagement.security;

        import org.springframework.context.annotation.Bean;
        import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.crypto.password.NoOpPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;

/*
The @EnableWebSecurity annotation is important if we disable the default security configuration.
the application will fail to start. The annotation is only optional if we're just overriding
the default behavior using a WebSecurityConfigurerAdapter.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    /*
    Authentication
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("qq").password("qq").roles("USER")
                .and().withUser("ww").password("qq").roles("ADMIN");
    }

    @Bean
    public PasswordEncoder getPasswordEncoding() {
        return NoOpPasswordEncoder.getInstance();
    }
    /*
    Authorization
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/**").hasRole("ADMIN").and().formLogin();
    }



}
