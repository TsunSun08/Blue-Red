package pe.edu.upc.demosi.securities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@EnableMethodSecurity
public class SegurityConfig {
    private final UserDetailsService userDetailsService;

    public SegurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration configuration)
            throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();

        // Origen permitido: aplicación Angular
        configuration.setAllowedOrigins(
                List.of("http://localhost:4200", "https://onrender.com")
        );


        // Métodos HTTP permitidos
        configuration.setAllowedMethods(
                List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
        );

        // Cabeceras HTTP permitidas
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Cabeceras que Angular puede leer en la respuesta
        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        // No se utilizan cookies de autenticación
        configuration.setAllowCredentials(false);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        // Aplicar CORS a todos los endpoints
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .cors(Customizer.withDefaults())

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )


                .authorizeHttpRequests(auth -> auth

                        //login publico
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        //swagger publico
                        .requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/especies").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/especies/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/lote-captura").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/lote-captura/**").permitAll()

                        .requestMatchers(HttpMethod.DELETE, "/api/lote-captura").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/lote-captura/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/api/lote-captura").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/lote-captura/**").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/api/lote-captura").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/lote-captura/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/usuarios").permitAll()





                        // CORS es necesario para que el navegador pueda hacer peticiones a nuestro backend desde otro dominio
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        //cualquier otra peticion requiere autenticacion osea token
                        .anyRequest().authenticated()

                )

                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        new CustomJwtAuthenticationConverter()
                                )
                        )
                );



        return http.build();
    }

}
