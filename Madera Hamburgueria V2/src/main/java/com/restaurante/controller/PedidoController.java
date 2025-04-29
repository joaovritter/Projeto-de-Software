package com.restaurante.controller;

import com.restaurante.model.*;
import com.restaurante.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private MesaRepository mesaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String selecionarMesa(Model model) {
        List<Mesa> mesas = mesaRepository.findAll();
        model.addAttribute("mesas", mesas);
        return "pedido/selecionar-mesa";
    }

    @GetMapping("/{mesaId}")
    public String mostrarCardapio(@PathVariable Long mesaId, Model model) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        List<Produto> produtos = produtoRepository.findByDisponivelTrue();
        Pedido pedidoAtual = pedidoRepository.findByMesaAndFinalizadoFalse(mesa)
                .stream()
                .findFirst()
                .orElse(new Pedido());
                
        if (pedidoAtual.getId() == null) {
            pedidoAtual.setMesa(mesa);
            pedidoAtual.setDataHora(LocalDateTime.now());
            pedidoAtual = pedidoRepository.save(pedidoAtual);
        }
                
        model.addAttribute("mesa", mesa);
        model.addAttribute("produtos", produtos);
        model.addAttribute("pedido", pedidoAtual);
        
        return "pedido/cardapio";
    }

    @PostMapping("/{mesaId}/adicionar")
    public String adicionarItem(@PathVariable Long mesaId,
                              @RequestParam Long produtoId,
                              @RequestParam Integer quantidade,
                              @RequestParam(required = false) String observacoes) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
                
        Pedido pedido = pedidoRepository.findByMesaAndFinalizadoFalse(mesa)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        ItemPedido item = new ItemPedido();
        item.setPedido(pedido);
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());
        item.setObservacoes(observacoes);
        item.calcularSubtotal();
        
        pedido.getItens().add(item);
        pedido.calcularTotal();
        
        pedidoRepository.save(pedido);
        
        return "redirect:/pedido/" + mesaId;
    }

    @PostMapping("/{mesaId}/remover/{itemId}")
    public String removerItem(@PathVariable Long mesaId, @PathVariable Long itemId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        Pedido pedido = pedidoRepository.findByMesaAndFinalizadoFalse(mesa)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        pedido.getItens().removeIf(item -> item.getId().equals(itemId));
        pedido.calcularTotal();
        
        pedidoRepository.save(pedido);
        
        return "redirect:/pedido/" + mesaId;
    }

    @PostMapping("/{mesaId}/finalizar")
    public String finalizarPedido(@PathVariable Long mesaId) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        Pedido pedido = pedidoRepository.findByMesaAndFinalizadoFalse(mesa)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        pedido.setFinalizado(true);
        pedido.setStatus(Pedido.StatusPedido.CONCLUIDO);
        pedidoRepository.save(pedido);
        
        return "redirect:/pedido/" + mesaId + "/confirmacao";
    }

    @GetMapping("/{mesaId}/confirmacao")
    public String mostrarConfirmacao(@PathVariable Long mesaId, Model model) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        
        List<Pedido> pedidosFinalizados = pedidoRepository.findByMesaAndFinalizadoTrue(mesa);
        
        model.addAttribute("mesa", mesa);
        model.addAttribute("pedidos", pedidosFinalizados);
        
        return "pedido/confirmacao";
    }
} 