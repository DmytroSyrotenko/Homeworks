package com.example.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


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

    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    //ето тот класс который срабатывает перед всеми и решает допуски к ендпоинтам?
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                //TODO оазобраться с тем что не работает-не пропускает из за **?  get permission against name
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/api/open/**", "/api/auth/**", "/swagger-ui.html").permitAll()
                                .requestMatchers(GET, "/api/personal/**").hasAuthority(PERSONAL_READ.getPermission())
                                .requestMatchers(POST, "/api/personal/**").hasAuthority(PERSONAL_CREATE.getPermission())
                                .requestMatchers(PUT, "/api/personal/**").hasAuthority(PERSONAL_UPDATE.getPermission())
                                .requestMatchers(DELETE, "/api/personal/**").hasAuthority(PERSONAL_DELETE.getPermission())

                                .requestMatchers(GET, "/api/admin/**").hasAuthority(ADMIN_READ.getPermission())
                                .requestMatchers(POST, "/api/admin/**").hasAuthority(ADMIN_CREATE.getPermission())
                                .requestMatchers(PUT, "/api/admin/**").hasAuthority(ADMIN_UPDATE.getPermission())
                                .requestMatchers(DELETE, "/api/admin/**").hasAuthority(ADMIN_DELETE.getPermission())
                                .anyRequest().authenticated()
                        //прописали импорт констант прямо в класс
                ).authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
