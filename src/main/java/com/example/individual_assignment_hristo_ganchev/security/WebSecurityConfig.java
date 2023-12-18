package com.example.individual_assignment_hristo_ganchev.security;

import com.example.individual_assignment_hristo_ganchev.security.auth.AuthenticationRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity,
                                           AuthenticationEntryPoint authenticationEntryPoint,
                                           AuthenticationRequestFilter authenticationRequestFilter) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(configurer ->
                        configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers( "/ws/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/tickets", "/concerts", "/users", "/users/login", "/orders", "/tokens").permitAll()
                                .requestMatchers(HttpMethod.GET, "/tickets", "/concerts", "/concerts/filter","/orders", "/tokens" , "/users", "/users/viaToken" , "/orders/all").permitAll()
                                .requestMatchers(
                                        new RegexRequestMatcher("\\/users/[0-9]+", "GET"),
                                        new RegexRequestMatcher("\\/users/ban/[0-9]+", "PUT"),
                                        new RegexRequestMatcher("\\/users/unban/[0-9]+", "PUT"),
                                        new RegexRequestMatcher("\\/concerts/[0-9]+", "GET"),
                                        new RegexRequestMatcher("\\/concerts/[0-9]+", "PUT"),
                                        new RegexRequestMatcher("\\/orders/[0-9]+", "GET"),
                                        new RegexRequestMatcher("\\/orders", "POST"),
                                        new RegexRequestMatcher("\\/users/admin/[0-9]+", "DELETE"),
                                        new RegexRequestMatcher("\\/tickets/[0-9]+", "GET")).permitAll()
                                .requestMatchers(HttpMethod.PUT, "/tickets", "/concerts", "/users", "/users/ban", "/users/unban", "/orders", "/tokens").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/tickets", "/concerts", "/users", "/users/admin", "/orders", "/tokens").permitAll()
                                .requestMatchers("/ws").permitAll()
                                .anyRequest().authenticated()
                )
                .exceptionHandling(configure -> configure.authenticationEntryPoint(authenticationEntryPoint))
                .addFilterBefore(authenticationRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:5173", "http://localhost:4173");
            }
        };
    }
}