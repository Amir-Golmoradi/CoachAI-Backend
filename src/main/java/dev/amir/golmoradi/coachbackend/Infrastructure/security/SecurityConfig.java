package dev.amir.golmoradi.coachbackend.Infrastructure.security;

import dev.amir.golmoradi.coachbackend.Infrastructure.security.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static dev.amir.golmoradi.coachbackend.Core.Constants.WHITE_LIST_URL;
import static dev.amir.golmoradi.coachbackend.Domain.enums.RolePermissions.*;
import static dev.amir.golmoradi.coachbackend.Domain.enums.Roles.ADMIN;
import static dev.amir.golmoradi.coachbackend.Domain.enums.Roles.COACH;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ApplicationConfig config;
    private final LogoutHandler logoutHandler;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter,
                          ApplicationConfig config, LogoutHandler logoutHandler) {
        this.config = config;
        this.jwtRequestFilter = jwtRequestFilter;
        this.logoutHandler = logoutHandler;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttp -> {
                    authorizeHttp.requestMatchers(WHITE_LIST_URL).permitAll()
                            .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), COACH.name())
                            .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), COACH_READ.name())
                            .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), COACH_CREATE.name())
                            .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), COACH_UPDATE.name())
                            .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), COACH_DELETE.name());
                    authorizeHttp.anyRequest().fullyAuthenticated();
                })
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(config.authProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout
                                .logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> clearContext()));

        return http.build();
    }
}
