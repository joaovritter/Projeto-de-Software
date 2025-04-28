package com.example.madera.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Controlador responsável por gerenciar o acesso às imagens dos produtos.
 * Este controlador fornece endpoints para servir imagens armazenadas no sistema.
 */
@RestController
@RequestMapping("/api/imagens")
public class ImagemController {

    /**
     * Diretório onde as imagens dos produtos são armazenadas.
     */
    private final Path uploadDir = Paths.get("src/main/resources/static/images/produtos");

    /**
     * Endpoint para servir imagens de produtos.
     * 
     * @param filename Nome do arquivo de imagem a ser servido
     * @return ResponseEntity contendo a imagem ou erro apropriado
     */
    @GetMapping("/produtos/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = uploadDir.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                // Determinar o tipo de mídia com base na extensão do arquivo
                String contentType = determineContentType(filename);
                
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    /**
     * Determina o tipo de mídia com base na extensão do arquivo.
     * 
     * @param filename Nome do arquivo
     * @return String representando o tipo de mídia
     */
    private String determineContentType(String filename) {
        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "webp":
                return "image/webp";
            default:
                return "application/octet-stream";
        }
    }
} 