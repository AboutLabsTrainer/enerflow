package de.saki.enerflow.config;

import de.saki.enerflow.config.security.JwtAuthenticationFilter;
import de.saki.enerflow.core.service.EnerFlowUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

/**
 * Defines who may call which HTTP route, and that the application keeps
 * no HTTP session. Each request must prove itself with a JWT. The filter
 * that reads the token is inserted here, before Spring's own login filter,
 * so authorization rules see a user rather than an empty context.
 *
 * Spring Security configuration for EnerFlow.
 *
 * <p>Chunk 1 (AP3.3): replaces the implicit Spring Boot in-memory user with
 * real database-backed authentication via {@link EnerFlowUserDetailsService}
 * and BCrypt password hashing. HTTP Basic is kept as the transport for now —
 * JWT token authentication follows in Chunk 2.
 *
 * <p>Session management is set to STATELESS already, because EnerFlow's REST
 * API is designed to be stateless: no server-side session is created or used.
 * Each request must carry its own credentials (Basic Auth in Chunk 1, JWT
 * Bearer token from Chunk 2 onward).
 *
 * @author saki
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final EnerFlowUserDetailsService userDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(EnerFlowUserDetailsService userDetailsService,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Exposes Spring's AuthenticationManager as a Bean so it can be injected
     * into the login endpoint (Chunk 2, AP3.3).
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        PathPatternRequestMatcher.withDefaults().matcher("/api/**")))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/login").permitAll()
                        // Thymeleaf dashboard page and static assets
                        .requestMatchers("/dashboard").permitAll()
                        .requestMatchers("/css/**", "/js/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                )
                // This filter must run before Spring's username/password filter.
                // It turns "Authorization: Bearer" into a user in the SecurityContext.
                // Authorization then sees that user. If this ran later, every
                // request would look anonymous.
                .addFilterBefore(jwtAuthenticationFilter,
                        org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}