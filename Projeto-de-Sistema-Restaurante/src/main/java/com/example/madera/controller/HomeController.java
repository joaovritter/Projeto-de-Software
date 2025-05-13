package com.example.madera.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável pela página inicial do sistema
 */
@Controller
public class HomeController {

    /**
     * Endpoint para a página inicial
     * Retorna a view home com as opções de acesso
     */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * Endpoint para a área do cliente
     * Redireciona para a página de novo pedido
     */
    @GetMapping("/cliente/mesas")
    public String areaCliente() {
        return "redirect:/pedidos/novo";
    }
} 