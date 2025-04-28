package com.example.madera.model;

// Enumeração que define os possíveis estados de um pedido no sistema
// Cada estado representa uma fase diferente do ciclo de vida do pedido
public enum StatusPedido {
    ABERTO,        // Pedido foi criado e está aguardando processamento
    EM_PRODUCAO,   // Pedido está sendo preparado
    CANCELADO,     // Pedido foi cancelado
    FINALIZADO     // Pedido foi concluído e pago
} 