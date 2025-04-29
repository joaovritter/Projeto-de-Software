package com.restaurante.controller;

import com.restaurante.model.Produto;
import com.restaurante.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/admin/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    private final String uploadDir = "uploads/produtos/";

    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoRepository.findAll());
        return "admin/produtos/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("produto", new Produto());
        model.addAttribute("categorias", Produto.CategoriaProduto.values());
        return "admin/produtos/formulario";
    }

    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, @RequestParam("imagem") MultipartFile imagem) {
        if (!imagem.isEmpty()) {
            try {
                String nomeArquivo = UUID.randomUUID().toString() + "_" + imagem.getOriginalFilename();
                Path uploadPath = Paths.get(uploadDir);
                
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                
                Path arquivo = uploadPath.resolve(nomeArquivo);
                Files.copy(imagem.getInputStream(), arquivo);
                
                produto.setImagemUrl("/uploads/produtos/" + nomeArquivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        produtoRepository.save(produto);
        return "redirect:/admin/produtos";
    }

    @GetMapping("/{id}/editar")
    public String editarProduto(@PathVariable Long id, Model model) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                
        model.addAttribute("produto", produto);
        model.addAttribute("categorias", Produto.CategoriaProduto.values());
        return "admin/produtos/formulario";
    }

    @PostMapping("/{id}/disponibilidade")
    public String alterarDisponibilidade(@PathVariable Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                
        produto.setDisponivel(!produto.isDisponivel());
        produtoRepository.save(produto);
        
        return "redirect:/admin/produtos";
    }

    @PostMapping("/{id}/excluir")
    public String excluirProduto(@PathVariable Long id) {
        produtoRepository.deleteById(id);
        return "redirect:/admin/produtos";
    }
} 