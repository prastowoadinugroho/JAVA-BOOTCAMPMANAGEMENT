package com.bootcampmanagement.clientapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        
    }
    
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        // http builder configurations for authorize requests and form login (see below)
        http
            .csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/logout").authenticated()
            .antMatchers("/bootcamp-management/**").authenticated()
            .and()
            .logout().disable()
            .formLogin()
            .loginPage("/login").permitAll();         
    }
}