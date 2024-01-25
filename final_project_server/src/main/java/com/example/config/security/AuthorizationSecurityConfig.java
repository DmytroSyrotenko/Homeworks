package com.example.config.security;

import com.example.persistence.type.RoleType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpMethod.DELETE;

import static com.example.persistence.type.PermissionType.ADMIN_CREATE;
import static com.example.persistence.type.PermissionType.ADMIN_READ;
import static com.example.persistence.type.PermissionType.ADMIN_UPDATE;
import static com.example.persistence.type.PermissionType.ADMIN_DELETE;

import static com.example.persistence.type.PermissionType.PERSONAL_CREATE;
import static com.example.persistence.type.PermissionType.PERSONAL_READ;
import static com.example.persistence.type.PermissionType.PERSONAL_UPDATE;
import static com.example.persistence.type.PermissionType.PERSONAL_DELETE;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class AuthorizationSecurityConfig {

    private final  AuthenticationProvider authenticationProvider;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/open/**","/api/auth/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(POST, "/api/personal/**").hasAuthority(PERSONAL_CREATE.name())
                        .requestMatchers(GET, "/api/personal/**").hasAuthority(PERSONAL_READ.name())
                        .requestMatchers(PUT, "/api/personal/**").hasAuthority(PERSONAL_UPDATE.name())
                        .requestMatchers(DELETE, "/api/personal/**").hasAuthority(PERSONAL_DELETE.name())

                        .requestMatchers(POST, "/api/admin/**").hasAuthority(ADMIN_CREATE.name())
                        .requestMatchers(GET, "/api/admin/**").hasAuthority(ADMIN_READ.name())
                        .requestMatchers(PUT, "/api/admin/**").hasAuthority(ADMIN_UPDATE.name())
                        .requestMatchers(DELETE, "/api/admin/**").hasAuthority(ADMIN_DELETE.name())
                        .anyRequest().authenticated()
                        //прописали импорт констант прямо в класс
                ).authenticationProvider(authenticationProvider);

        return http.build();
    }


}
