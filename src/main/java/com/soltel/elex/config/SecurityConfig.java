package com.soltel.elex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filtrado(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())

                        .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/inicio/**","/tipos_expediente/**", "/expedientes/**", "/actuaciones/**","/documentos/**", "/busquedas/**").permitAll()
                        .anyRequest().authenticated())


                .formLogin(form -> form
                .defaultSuccessUrl("/swagger-ui/index.html", true));

        return http.build();
    }

    @Bean
    public PasswordEncoder codificador() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService servicio(PasswordEncoder codificador) {
        UserDetails usuarioAdmin = User.builder()
                .username("admin")
                .password(codificador.encode("admin"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(usuarioAdmin);
    }
}