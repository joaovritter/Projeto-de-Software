package com.joaozao.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthoritiesAuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/","/home", "/style.css", "/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin((FormLoginConfigurer<HttpSecurity> form) -> form
                    .loginPage("/login")
                    .permitAll()
            )
            .logout((logout) -> logout.permitAll());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder() // User.withDefaultPasswordEncoder() é um método que cria um usuário com senha padrão
                .username("joaozao")
                .password("www.com.brj")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
