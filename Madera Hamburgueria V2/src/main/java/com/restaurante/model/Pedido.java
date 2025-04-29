package com.restaurante.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesa mesa;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();
    
    private LocalDateTime dataHora;
    private String observacoes;
    private boolean finalizado = false;
    
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    
    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.EM_ANDAMENTO;
    
    public enum StatusPedido {
        EM_ANDAMENTO,
        CONCLUIDO,
        CANCELADO
    }

    public void calcularTotal() {
        this.valorTotal = itens.stream()
                .map(ItemPedido::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 