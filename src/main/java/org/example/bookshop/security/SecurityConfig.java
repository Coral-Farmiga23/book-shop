package org.example.bookshop.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, CorsConfigurationSource corsConfigurationSource) throws Exception {
        http.formLogin(c -> {
            c.loginPage("/auth/login").permitAll();
            c.defaultSuccessUrl("/");
        });

        http.logout(c -> {
            c.logoutUrl("/logout").permitAll();
            c.logoutSuccessUrl("/");
        });

        http.cors(c -> {
            CorsConfigurationSource source = new CorsConfigurationSource() {
                @Override
                public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowCredentials(true);
                    config.setAllowedMethods(List.of("*"));
                    config.setExposedHeaders(List.of("*"));
                    config.setAllowedHeaders(List.of("*"));
                    config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:3000", "http://localhost:4200"));
                    return config;
                }
            };
            c.configurationSource(source);
        });

        http.csrf(c -> c.disable());
        http.authorizeHttpRequests(c ->{
            c.requestMatchers("/","/home/**", "/bootstrap/**", "/images/**", "/auth/register", "auth/checkout").permitAll();
            c.requestMatchers("/admin/**").hasRole("ADMIN");
            c.anyRequest().permitAll();
        });

        http.authenticationProvider(daoAuthenticationProvider());
        return http.build();
    }
}
