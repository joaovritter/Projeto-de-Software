package com.joaozao.arduinomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "ControleLed"; // Nome do arquivo HTML (sem .html)
    }
}
