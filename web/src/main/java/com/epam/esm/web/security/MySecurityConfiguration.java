package com.epam.esm.web.security;

import com.epam.esm.web.filter.MyJwtRequestFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.epam.esm.web.security.ConstantUrl.*;
import static com.epam.esm.web.security.ConstantUrl.URL_USER_USER;

@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final MyJwtRequestFilter jwtRequestFilter;

    public MySecurityConfiguration(@Qualifier("userServiceImpl") UserDetailsService userDetailsService,
                                   PasswordEncoder passwordEncoder,
                                   MyJwtRequestFilter jwtRequestFilter) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        URL_ALL_GIFT,
                        URL_ALL_TAG,
                        URL_ALL_USER,
                        URL_ALL_CONTRACT).permitAll()
                .antMatchers(
                        URL_USER_GIFT,
                        URL_USER_TAG,
                        URL_USER_USER,
                        URL_USER_CONTRACT).hasAnyRole(ROLE_USER, ROLE_ADMIN)
                .antMatchers(
                        URL_ADMIN_GIFT,
                        URL_ADMIN_TAG,
                        URL_ADMIN_USER,
                        URL_ADMIN_CONTRACT).hasRole(ROLE_ADMIN)
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
