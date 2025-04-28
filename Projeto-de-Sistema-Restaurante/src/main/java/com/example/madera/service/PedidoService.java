package com.example.madera.service;

import com.example.madera.model.Pedido;
import com.example.madera.model.StatusPedido;
import com.example.madera.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada aos pedidos.
 * Implementa operações CRUD e regras de negócio específicas.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    /**
     * Lista todos os pedidos cadastrados.
     * 
     * @return Lista de todos os pedidos
     */
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    /**
     * Busca um pedido pelo seu ID.
     * 
     * @param id ID do pedido
     * @return Pedido encontrado ou null se não existir
     */
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    /**
     * Salva um novo pedido ou atualiza um existente.
     * Se for um novo pedido, define a data de criação.
     * 
     * @param pedido Pedido a ser salvo
     * @return Pedido salvo
     */
    public Pedido salvar(Pedido pedido) {
        if (pedido.getId() == null) {
            pedido.setDataCriacao(LocalDateTime.now());
        }
        return pedidoRepository.save(pedido);
    }

    /**
     * Exclui um pedido pelo seu ID.
     * 
     * @param id ID do pedido a ser excluído
     */
    public void excluir(Long id) {
        pedidoRepository.deleteById(id);
    }

    /**
     * Lista pedidos por status.
     * 
     * @param status Status dos pedidos a serem listados
     * @return Lista de pedidos com o status especificado
     */
    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    /**
     * Atualiza o status de um pedido.
     * Se o novo status for FINALIZADO, define a data de finalização.
     * 
     * @param pedido Pedido a ser atualizado
     * @param novoStatus Novo status do pedido
     * @return Pedido atualizado
     */
    public Pedido atualizarStatus(Pedido pedido, StatusPedido novoStatus) {
        pedido.setStatus(novoStatus);
        if (novoStatus == StatusPedido.FINALIZADO) {
            pedido.setDataFinalizacao(LocalDateTime.now());
        }
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPorMesa(Long mesaId) {
        return pedidoRepository.findByMesaId(mesaId);
    }

    /**
     * Atualiza o status de um pedido pelo seu ID.
     * 
     * @param id ID do pedido
     * @param novoStatus Novo status do pedido
     * @return Pedido atualizado ou null se não encontrado
     */
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);
        if (pedido != null) {
            return atualizarStatus(pedido, novoStatus);
        }
        return null;
    }
} 