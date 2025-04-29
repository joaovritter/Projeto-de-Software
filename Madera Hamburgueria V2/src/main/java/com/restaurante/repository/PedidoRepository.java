package com.restaurante.repository;

import com.restaurante.model.Pedido;
import com.restaurante.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByMesa(Mesa mesa);
    List<Pedido> findByMesaAndFinalizadoFalse(Mesa mesa);
    List<Pedido> findByMesaAndFinalizadoTrue(Mesa mesa);
    List<Pedido> findByStatus(Pedido.StatusPedido status);
} 