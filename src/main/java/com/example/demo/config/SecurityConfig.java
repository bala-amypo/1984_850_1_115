// package com.example.demo.config;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;

// @Configuration
// public class SecurityConfig {
//   @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
// }
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**", "/logs/**", "/shipments/**", "/breaches/**", "/alerts/**", "/rules/**").permitAll()
                .anyRequest().permitAll() // allow everything for now
            )
            .formLogin(login -> login.disable()) // disable default login page
            .httpBasic(basic -> basic.disable()); // disable basic auth too
        return http.build();
    }
}