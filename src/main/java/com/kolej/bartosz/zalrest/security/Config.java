package com.kolej.bartosz.zalrest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Config extends WebSecurityConfigurerAdapter {

    private static final String[] WHITELIST = {
            "/configuration/ui",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/webjars/**",
            "/h2-console/**"
    };
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
    private final RestAuthFailHandler restAuthFailHandler;
    private final RestAuthSuccessHandler restAuthSuccessHandler;
    private final AuthenticationManager customAuthenticationManager;
    private final JWTAuthFilter jwtAuthFilter;

    @Autowired
    public Config(DataSource dataSource, ObjectMapper objectMapper, RestAuthFailHandler restAuthFailHandler,
                  RestAuthSuccessHandler restAuthSuccessHandler, AuthenticationManager customAuthenticationManager, JWTAuthFilter jwtAuthFilter) {
        this.dataSource = dataSource;
        this.objectMapper = objectMapper;
        this.restAuthFailHandler = restAuthFailHandler;
        this.restAuthSuccessHandler = restAuthSuccessHandler;
        this.customAuthenticationManager = customAuthenticationManager;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("configure auth");
        auth.jdbcAuthentication()
                .dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("configure");
        http.csrf().disable();
        http.authorizeRequests()
//                .antMatchers(WHITELIST).permitAll()
//                .antMatchers("/wordUser/**").hasRole("USER")
//                .antMatchers("/word/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authFilter())
                .addFilter(jwtAuthFilter)
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .headers().frameOptions().sameOrigin();
    }

    public CustomUsernamePasswordAuthenticationFilter authFilter() {
        System.out.println("authFilter");
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(objectMapper);
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(restAuthSuccessHandler);
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(restAuthFailHandler);
        customUsernamePasswordAuthenticationFilter.setAuthenticationManager(customAuthenticationManager);
        return customUsernamePasswordAuthenticationFilter;
    }
}
