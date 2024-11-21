package com.example.api_gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/usuarios", "/usuarios/login", "/usuarios/add").permitAll()

                .pathMatchers("/monopatines/uso-por-kilometros").hasAuthority("MANTENIMIENTO")
                .pathMatchers("/cuentas/{id}/cambiar-estado").hasAuthority("ADMIN")
                .pathMatchers("/viajes/monopatines-con-mas-viajes").hasAuthority("ADMIN")
                .pathMatchers("/viajes/total-facturado").hasAuthority("ADMIN")
                .pathMatchers("/monopatines/total-en-operacion-y-mantenimiento").hasAuthority("ADMIN")
                .pathMatchers("/viajes/ajustar-precios").hasAuthority("ADMIN")
                .pathMatchers("/monopatines/cercanos/{latitud}/{longitud}/{radio}").hasAuthority("ADMIN")


                .anyExchange().authenticated()
            )
            .addFilterAt(new JwtAuthenticationFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION)
            .build();
    }
}
