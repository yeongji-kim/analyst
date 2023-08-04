package com.analyze.analyst.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .csrf((csrf)-> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers("/member/**").hasRole("MEMBER")
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll()
        )
        .sessionManagement(sessionManagement ->
            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .formLogin((login)->login
            .loginPage("/signIn")
            .loginProcessingUrl("/main")
            .usernameParameter("memberId")
            .passwordParameter("password")
        )
        .logout(logout->logout
            .logoutSuccessUrl("/dashboard")
        );
       
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() throws Exception {
        return new BCryptPasswordEncoder();
    }

}
