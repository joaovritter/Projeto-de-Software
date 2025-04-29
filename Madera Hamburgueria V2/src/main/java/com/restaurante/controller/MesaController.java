package com.restaurante.controller;

import com.restaurante.model.Mesa;
import com.restaurante.model.Pedido;
import com.restaurante.repository.MesaRepository;
import com.restaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping
    public String listarMesas(Model model) {
        model.addAttribute("mesas", mesaRepository.findAll());
        return "admin/mesas/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("mesa", new Mesa());
        return "admin/mesas/formulario";
    }

    @PostMapping("/salvar")
    public String salvarMesa(@ModelAttribute Mesa mesa) {
        mesaRepository.save(mesa);
        return "redirect:/admin/mesas";
    }

    @GetMapping("/{id}/editar")
    public String editarMesa(@PathVariable Long id, Model model) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        model.addAttribute("mesa", mesa);
        return "admin/mesas/formulario";
    }

    @PostMapping("/{id}/ocupacao")
    public String alterarOcupacao(@PathVariable Long id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
                
        mesa.setOcupada(!mesa.isOcupada());
        mesaRepository.save(mesa);
        
        return "redirect:/admin/mesas";
    }

    @PostMapping("/{id}/excluir")
    public String excluirMesa(@PathVariable Long id) {
        mesaRepository.deleteById(id);
        return "redirect:/admin/mesas";
    }

    @GetMapping("/{id}/pedidos")
    public String verPedidos(@PathVariable Long id, Model model) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mesa não encontrada"));
        
        List<Pedido> pedidosAtivos = pedidoRepository.findByMesaAndFinalizadoFalse(mesa);
        List<Pedido> pedidosFinalizados = pedidoRepository.findByMesaAndFinalizadoTrue(mesa);
        
        model.addAttribute("mesa", mesa);
        model.addAttribute("pedidosAtivos", pedidosAtivos);
        model.addAttribute("pedidosFinalizados", pedidosFinalizados);
        
        return "admin/mesas/pedidos";
    }

    @PostMapping("/pedidos/{pedidoId}/status")
    public String atualizarStatusPedido(@PathVariable Long pedidoId, 
                                       @RequestParam Pedido.StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        
        pedido.setStatus(novoStatus);
        if (novoStatus == Pedido.StatusPedido.CONCLUIDO) {
            pedido.setFinalizado(true);
        }
        
        pedidoRepository.save(pedido);
        
        return "redirect:/admin/mesas/" + pedido.getMesa().getId() + "/pedidos";
    }
} 