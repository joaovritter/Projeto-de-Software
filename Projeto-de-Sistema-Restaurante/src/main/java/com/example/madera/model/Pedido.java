package com.example.madera.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

// Entidade que representa um pedido no sistema
// Mapeia a tabela 'pedidos' no banco de dados
@Data
@Entity
@Table(name = "pedidos")
public class Pedido {
    
    // Identificador único do pedido
    // Gerado automaticamente pelo banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // Mesa associada ao pedido
    // Relacionamento muitos-para-um com a entidade Mesa
    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;
    
    // Lista de produtos no pedido
    // Relacionamento muitos-para-muitos com a entidade Produto
    // Usa uma tabela intermediária 'pedido_produtos'
    @ManyToMany
    @JoinTable(
        name = "pedido_produtos",
        joinColumns = @JoinColumn(name = "pedido_id"),
        inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos;
    
    // Status atual do pedido
    // Armazenado como string no banco de dados
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
    
    // Data e hora em que o pedido foi criado
    // Campo obrigatório
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    
    // Data e hora em que o pedido foi finalizado
    // Pode ser nulo se o pedido ainda estiver em andamento
    private LocalDateTime dataFinalizacao;
    
    // Valor total do pedido
    // Campo obrigatório
    @Column(nullable = false)
    private Double valorTotal;
    
    // Observações sobre o pedido
    // Limite de 500 caracteres
    @Column(length = 500)
    private String observacao;
    
    // Indica se há taxa de garçom
    // Campo obrigatório
    @Column(nullable = false)
    private Boolean taxaGarcom;
} 