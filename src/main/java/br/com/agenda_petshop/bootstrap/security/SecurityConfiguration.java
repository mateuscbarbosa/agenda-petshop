package br.com.agenda_petshop.bootstrap.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final SecurityFilter securityFilter;

    public SecurityConfiguration(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, ALLOWEDFORALL).permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/list").hasAnyRole("ADMIN", "READ_USER")
                        .requestMatchers(HttpMethod.POST, "/users/create").hasAnyRole("ADMIN", "CREATE_USER")
                        .requestMatchers(HttpMethod.PUT, "/users/selfupdate").hasAnyRole("ADMIN","COMMON")
                        .requestMatchers(HttpMethod.PUT, "/users/*/update").hasAnyRole("ADMIN", "UPDATE_USER")
                        .requestMatchers(HttpMethod.PATCH, "/users/*/resetpassword").hasAnyRole("ADMIN", "UPDATE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/users/*/inactivate").hasAnyRole("ADMIN", "DELETE_USER")
                        .requestMatchers(HttpMethod.PUT, "/users/firstpassword").hasAnyRole("ADMIN","COMMON")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    private static final String [] ALLOWEDFORALL = {
      "/auth"
    };

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
