package com.excilys.computerdatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by excilys on 24/05/17.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("Baba").password("Baba").roles("USER");
        auth.inMemoryAuthentication().withUser("JL").password("Croissants").roles("ADMIN");
       
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/Dashboard").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/Dashboard/delete").access("hasRole('ROLE_ADMIN')")         
                .antMatchers("/addComputer").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/editComputer").access("hasRole('ROLE_ADMIN')")
                .and().formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}