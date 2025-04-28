package com.example.madera.controller;

import com.example.madera.model.Produto;
import com.example.madera.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Controlador responsável por gerenciar todas as operações relacionadas a produtos
 * Mapeia todas as requisições para o caminho /produtos
 */
@Controller
@RequestMapping("/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    /**
     * Diretório onde as imagens dos produtos são armazenadas
     */
    private final String UPLOAD_DIR = "src/main/resources/static/images/produtos/";

    /**
     * Endpoint para listar todos os produtos
     * Retorna a view produtos/lista com a lista de produtos
     */
    @GetMapping
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodos());
        return "produtos/lista";
    }

    /**
     * Endpoint para exibir o formulário de novo produto
     * Retorna a view produtos/form com um produto vazio
     */
    @GetMapping("/novo")
    public String novoProduto(Model model) {
        model.addAttribute("produto", new Produto());
        return "produtos/form";
    }

    /**
     * Endpoint para salvar um produto (novo ou existente)
     * Processa o upload de imagem se houver
     * Redireciona para a lista de produtos após salvar
     */
    @PostMapping
    public String salvarProduto(@ModelAttribute Produto produto, @RequestParam(value = "imagem", required = false) MultipartFile imagem) {
        // Se for uma edição, buscar o produto existente
        if (produto.getId() != null) {
            Produto produtoExistente = produtoService.buscarPorId(produto.getId());
            if (produtoExistente != null) {
                // Manter a URL da imagem existente se nenhuma nova imagem for enviada
                if (imagem == null || imagem.isEmpty()) {
                    produto.setImagemUrl(produtoExistente.getImagemUrl());
                }
            }
        }
        
        // Processar nova imagem apenas se uma for enviada
        if (imagem != null && !imagem.isEmpty()) {
            try {
                // Criar o diretório se não existir
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Gerar um nome único para o arquivo
                String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(imagem.getOriginalFilename());
                
                // Salvar o arquivo
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(imagem.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                
                // Atualizar a URL da imagem no produto
                produto.setImagemUrl("/images/produtos/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        produtoService.salvar(produto);
        return "redirect:/produtos";
    }

    /**
     * Endpoint para exibir o formulário de edição de produto
     * Retorna a view produtos/form com o produto carregado
     */
    @GetMapping("/editar/{id}")
    public String editarProduto(@PathVariable Long id, Model model) {
        model.addAttribute("produto", produtoService.buscarPorId(id));
        return "produtos/form";
    }

    /**
     * Endpoint para excluir um produto
     * Remove a imagem associada se existir
     * Redireciona para a lista de produtos após excluir
     */
    @GetMapping("/excluir/{id}")
    public String excluirProdido(@PathVariable Long id) {
        Produto produto = produtoService.buscarPorId(id);
        if (produto != null && produto.getImagemUrl() != null) {
            try {
                Path imagePath = Paths.get("src/main/resources/static" + produto.getImagemUrl());
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        produtoService.excluir(id);
        return "redirect:/produtos";
    }

    /**
     * Endpoint para servir as imagens dos produtos
     */
    @GetMapping("/imagem/{filename:.+}")
    public void getImage(@PathVariable String filename, HttpServletResponse response) throws IOException {
        Path imagePath = Paths.get(UPLOAD_DIR + filename);
        if (Files.exists(imagePath)) {
            response.setContentType("image/jpeg");
            Files.copy(imagePath, response.getOutputStream());
        }
    }
} 