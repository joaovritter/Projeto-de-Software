package com.example.madera.controller;

import com.example.madera.model.Mesa;
import com.example.madera.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para gerenciar as funcionalidades das mesas
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Controller
@RequestMapping("/mesas")
public class MesaController {
    @Autowired
    private MesaService mesaService;

    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaService.listarTodas());
        return "mesas/lista";
    }

    @GetMapping("/novo")
    public String novaMesa(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "mesas/form";
    }

    @PostMapping
    public String salvarMesa(@ModelAttribute Mesa mesa) {
        mesaService.salvar(mesa);
        return "redirect:/mesas";
    }

    @GetMapping("/excluir/{id}")
    public String excluirMesa(@PathVariable Long id) {
        mesaService.excluir(id);
        return "redirect:/mesas";
    }
} 