package com.mjwsolucoes.sistemanutricao.controller;

import com.mjwsolucoes.sistemanutricao.dto.CreateNutricionistaDto;
import com.mjwsolucoes.sistemanutricao.model.Estabelecimento;
import com.mjwsolucoes.sistemanutricao.model.Nutricionista;
import com.mjwsolucoes.sistemanutricao.service.EstabelecimentoService;
import com.mjwsolucoes.sistemanutricao.service.NutricionistaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/login")
public class HomeController {
    private NutricionistaService nutricionistaService;
    private EstabelecimentoService estabelecimentoService;

    @PostMapping("/create")
    public String createNutricionista(@ModelAttribute CreateNutricionistaDto nutricionistaDto) {
        Nutricionista nutricionista = CreateNutricionistaDto.toEntity(); // Converte o DTO para a entidade
        nutricionistaService.create(nutricionista); // Chama o serviço para salvar
        return "redirect:/login"; // Redireciona para a página de login
        }

}
