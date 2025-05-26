package com.joaozao.springsecurity.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home"); //home vai retornar a pagina home.html
        registry.addViewController("/").setViewName("home"); //home vai retornar a pagina home.html
        registry.addViewController("/hello").setViewName("hello"); //home vai retornar a pagina home.html
        registry.addViewController("/login").setViewName("login"); //login vai retornar a pagina login.html

    }
}
