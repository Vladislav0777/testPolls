package com.example.demo.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import javax.sql.DataSource;

@Getter
@Setter
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    private final DataSource dataSource;
    private final Environment environment;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        boolean securityEnabled = Boolean.parseBoolean(environment.getProperty("poll.security.enabled"));
        if (securityEnabled) {
            http.authorizeRequests()
                        .antMatchers("/").permitAll()
                        .antMatchers("/main/polls/**", "/main/answers/**").hasAnyRole(ROLE_USER, ROLE_ADMIN)
                        .antMatchers("/**").hasRole(ROLE_ADMIN)
                    .and().formLogin()
                        .defaultSuccessUrl("/main/persons", true).permitAll()
                    .and().logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                    .and().csrf()
            ;
        } else {
            http.authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .and().csrf()
                    .disable().authorizeRequests().anyRequest().permitAll()
            ;
        }
    }
}