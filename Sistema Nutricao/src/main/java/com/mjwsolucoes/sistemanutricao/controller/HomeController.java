package com.mjwsolucoes.sistemanutricao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "index"; // aponta para um arquivo `index.html` no `templates`
    }

    @GetMapping("/login")
    public String login() {
        return "index";
    }
}
