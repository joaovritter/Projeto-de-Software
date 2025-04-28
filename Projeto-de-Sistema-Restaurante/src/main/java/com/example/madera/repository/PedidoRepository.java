package com.example.madera.repository;

import com.example.madera.model.Pedido;
import com.example.madera.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório para operações de banco de dados relacionadas à entidade Pedido.
 * Fornece métodos para persistência e consulta de pedidos.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    
    /**
     * Busca pedidos por status.
     * 
     * @param status Status dos pedidos a serem buscados
     * @return Lista de pedidos com o status especificado
     */
    List<Pedido> findByStatus(StatusPedido status);
    List<Pedido> findByMesaId(Long mesaId);
} 