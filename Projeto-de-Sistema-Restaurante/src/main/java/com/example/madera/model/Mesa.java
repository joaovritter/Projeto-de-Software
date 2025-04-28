package com.example.madera.model;

import jakarta.persistence.*;
import lombok.Data;

// Entidade que representa uma mesa no restaurante
// Mapeia a tabela 'mesas' no banco de dados
@Data
@Entity
@Table(name = "mesas")
public class Mesa {
    
    // Identificador único da mesa
    // Gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Número da mesa no restaurante
    // Usado para identificação visual da mesa
    private Integer numero;
} 