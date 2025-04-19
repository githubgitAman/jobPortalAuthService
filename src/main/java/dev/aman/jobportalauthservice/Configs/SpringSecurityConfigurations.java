package dev.aman.jobportalauthservice.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfigurations {
    //Bydefault Spring Security Config dependency secures all endpoints so need to add some filters
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        //Allowing all endpoint APIs
                        .anyRequest().permitAll() // 👈 Allow unauthenticated access
                )
                .build();
    }
}
