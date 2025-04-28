package com.example.madera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador para gerenciar as funcionalidades do funcionário
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    /**
     * Exibe a página inicial do funcionário
     * 
     * @return Nome da página HTML a ser renderizada
     */
    @GetMapping
    public String home() {
        return "funcionario/home";
    }

    @GetMapping("/mesas")
    public String mesas() {
        return "redirect:/mesas";
    }

    @GetMapping("/produtos")
    public String produtos() {
        return "redirect:/produtos";
    }

    @GetMapping("/pedidos")
    public String pedidos() {
        return "redirect:/pedidos";
    }
} 