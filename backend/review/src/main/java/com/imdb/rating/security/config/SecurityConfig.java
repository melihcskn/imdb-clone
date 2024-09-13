package com.imdb.rating.security.config;

import com.imdb.rating.security.jwt.JwtAuthConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    SecurityConfig(JwtAuthConverter jwtAuthConverter){
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(auth ->
                       auth
                               .requestMatchers("api/reviews/**")
                               .permitAll()
                               .anyRequest()
                               .authenticated())
               .oauth2ResourceServer(oauth2 -> oauth2
                       .jwt(jwtConfigurer -> jwtConfigurer
                               .jwtAuthenticationConverter(jwtAuthConverter)))
               .sessionManagement(sessionManagementConfigurer -> sessionManagementConfigurer
                       .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .build();
    }
}

