package com.example.madera.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa um produto no sistema.
 * Esta classe mapeia a tabela de produtos no banco de dados.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Data
@Entity
@Table(name = "produtos")
public class Produto {
    
    /**
     * Identificador único do produto.
     * Gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Nome do produto.
     * Campo obrigatório.
     */
    @Column(nullable = false)
    private String nome;
    
    /**
     * Categoria do produto.
     * Campo obrigatório.
     */
    @Column(nullable = false)
    private String categoria;
    
    /**
     * Valor do produto.
     * Campo obrigatório.
     */
    @Column(nullable = false)
    private Double valor;
    
    /**
     * Descrição detalhada do produto.
     * Limite de 500 caracteres.
     */
    @Column(length = 500)
    private String descricao;
    
    /**
     * URL da imagem do produto.
     * Armazena o caminho relativo para a imagem no sistema de arquivos.
     */
    @Column(name = "imagem_url")
    private String imagemUrl;
} 