package com.ecommerce.productManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /**
     * Configure authentication with an in-memory user.
     *
     * @return UserDetailsService with a single user.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("root")
                .password(passwordEncoder().encode("root")) // Encode password with BCrypt
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Define the HTTP security configuration.
     *
     * @param http HttpSecurity object to configure HTTP security settings.
     * @return The SecurityFilterChain object for Spring Security.
     * @throws Exception if there is an error during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .requireCsrfProtectionMatcher(request -> false) // Disable CSRF for all endpoints
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**","/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/swagger-ui/index.html", true) // Redirect to Swagger on successful login
                )
                .httpBasic(httpBasic -> {
                });

        return http.build();
    }

    /**
     * Configure password encoding using BCrypt.
     *
     * @return PasswordEncoder instance.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Use BCrypt for password encoding
    }
}