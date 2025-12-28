// package com.example.demo.config;

// import com.example.demo.security.JwtUtil;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import java.util.List;


// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Value("${jwt.secret}")
//     private String jwtSecret;

//     @Value("${jwt.expiration}")
//     private long jwtExpiration;

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public JwtUtil jwtUtil() {
//         return new JwtUtil(jwtSecret, jwtExpiration);
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }

//     // @Bean
//     // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//     //     http
//     //             .csrf(csrf -> csrf.disable())
//     //             .authorizeHttpRequests(auth -> auth
//     //                     .requestMatchers("/auth/**", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
//     //                     .permitAll()
//     //                     .anyRequest().permitAll())
//     //             .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//     //     return http.build();

//     @Bean
// public CorsConfigurationSource corsConfigurationSource() {

//     CorsConfiguration config = new CorsConfiguration();
//     config.setAllowedOrigins(List.of("*"));
//     config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//     config.setAllowedHeaders(List.of("*"));
//     config.setAllowCredentials(false); // IMPORTANT

//     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//     source.registerCorsConfiguration("/**", config);
//     return source;
// }

// private final JwtAuthenticationFilter jwtAuthenticationFilter;

// public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//     this.jwtAuthenticationFilter = jwtAuthenticationFilter;
// }


// //   @Bean
// // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

// //     http
// //         .cors(cors -> {})   // explicitly enable
// //         .csrf(csrf -> csrf.disable())
// //         .sessionManagement(session ->
// //             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
// //         )
// //         .authorizeHttpRequests(auth -> auth
// //             .anyRequest().permitAll()
// //         );

// //     return http.build();
// // }

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//     http
//         .cors(cors -> {})
//         .csrf(csrf -> csrf.disable())
//         .sessionManagement(session ->
//             session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//         )
//         .authorizeHttpRequests(auth -> auth
//             .requestMatchers(
//                 "/auth/**",
//                 "/v3/api-docs/**",
//                 "/swagger-ui/**",
//                 "/swagger-ui.html"
//             ).permitAll()
//             .anyRequest().authenticated()
//         )
//         .addFilterBefore(
//             jwtAuthenticationFilter,
//             UsernamePasswordAuthenticationFilter.class
//         );

//     return http.build();
// }
// }

package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email, null, List.of()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}



