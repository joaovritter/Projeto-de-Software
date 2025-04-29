package com.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String descricao;
    private BigDecimal preco;
    
    @Enumerated(EnumType.STRING)
    private CategoriaProduto categoria;
    
    private String imagemUrl;
    private boolean disponivel;
    
    public enum CategoriaProduto {
        HAMBURGUER,
        REFRIGERANTE,
        SOBREMESA
    }
} 