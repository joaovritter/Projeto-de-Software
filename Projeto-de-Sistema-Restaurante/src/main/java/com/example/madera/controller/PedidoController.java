package com.example.madera.controller;

import com.example.madera.model.*;
import com.example.madera.service.PedidoService;
import com.example.madera.service.ProdutoService;
import com.example.madera.service.MesaService;
import com.example.madera.repository.MesaRepository;
import com.example.madera.repository.PedidoRepository;
import com.example.madera.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// Controlador responsável por gerenciar todas as operações relacionadas a pedidos
// Mapeia todas as requisições para o caminho /pedidos
@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    // Injeção de dependências dos serviços e repositórios necessários
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private MesaService mesaService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // Endpoint para listar todos os pedidos
    // Retorna a view pedidos/lista com a lista de pedidos
    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarTodos();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/lista";
    }

    // Endpoint para exibir o formulário de novo pedido
    // Carrega as listas de mesas e produtos disponíveis
    @GetMapping("/novo")
    public String novoPedido(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("mesas", mesaRepository.findAll());
        model.addAttribute("produtos", produtoRepository.findAll());
        return "pedidos/novo";
    }

    // Endpoint para criar um novo pedido
    // Recebe os dados do pedido e as quantidades dos produtos
    // Calcula o valor total e salva o pedido
    @PostMapping
    public String criarPedido(@ModelAttribute Pedido pedido, @RequestParam Map<String, String> quantidades) {
        double valorTotal = 0.0;
        
        // Calcula o valor total baseado nas quantidades de cada produto
        for (Map.Entry<String, String> entry : quantidades.entrySet()) {
            try {
                // Ignora entradas que não são IDs de produtos
                if (!entry.getKey().startsWith("quantidades[")) {
                    continue;
                }
                
                // Extrai o ID do produto da chave "quantidades[X]"
                String produtoIdStr = entry.getKey().substring(12, entry.getKey().length() - 1);
                Long produtoId = Long.parseLong(produtoIdStr);
                int quantidade = Integer.parseInt(entry.getValue());
                
                if (quantidade > 0) {
                    Produto produto = produtoRepository.findById(produtoId).orElse(null);
                    if (produto != null) {
                        valorTotal += produto.getValor() * quantidade;
                    }
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                // Ignora entradas inválidas
                continue;
            }
        }
        
        // Configura os dados iniciais do pedido
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setTaxaGarcom(false);
        pedido.setValorTotal(valorTotal);
        pedido.setDataCriacao(LocalDateTime.now());
        pedidoRepository.save(pedido);
        return "redirect:/pedidos/" + pedido.getId() + "/status";
    }

    // Endpoint para exibir o status de um pedido específico
    // Retorna a view pedidos/status com os detalhes do pedido
    @GetMapping("/{id}/status")
    public String statusPedido(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return "redirect:/pedidos";
        }
        model.addAttribute("pedido", pedido);
        return "pedidos/status";
    }

    // Endpoint para atualizar o status de um pedido
    // Redireciona para a lista de pedidos se o status for EM_PRODUCAO
    // Caso contrário, mantém na página de status
    @GetMapping("/{id}/atualizar-status")
    public String atualizarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        pedidoService.atualizarStatus(id, status);
        
        // Se o status for EM_PRODUCAO, redireciona para a lista de pedidos
        if (status == StatusPedido.EM_PRODUCAO) {
            return "redirect:/pedidos";
        }
        
        // Caso contrário, mantém o comportamento original
        return "redirect:/pedidos/" + id + "/status";
    }

    // Endpoint para cancelar um pedido
    // Só permite cancelar pedidos que não estejam finalizados
    @GetMapping("/{id}/cancelar")
    public String cancelarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido != null && pedido.getStatus() != StatusPedido.FINALIZADO) {
            pedidoService.atualizarStatus(id, StatusPedido.CANCELADO);
        }
        return "redirect:/pedidos";
    }

    // Endpoint para exibir a página de pagamento
    // Só permite pagamento de pedidos em produção
    @GetMapping("/{id}/pagamento")
    public String paginaPagamento(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            return "redirect:/pedidos";
        }
        
        // Verifica se o pedido está em um estado válido para pagamento
        if (pedido.getStatus() != StatusPedido.EM_PRODUCAO) {
            return "redirect:/pedidos";
        }
        
        model.addAttribute("pedido", pedido);
        return "pedidos/pagamento";
    }

    // Endpoint para finalizar um pedido
    // Calcula a taxa do garçom se aplicável e finaliza o pedido
    @PostMapping("/{id}/finalizar")
    public String finalizarPedido(@PathVariable Long id, @RequestParam(defaultValue = "false") Boolean taxaGarcom) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido != null) {
            // Calcula o valor total com a taxa do garçom se aplicável
            if (taxaGarcom) {
                double valorComTaxa = pedido.getValorTotal() * 1.1; // Adiciona 10%
                pedido.setValorTotal(valorComTaxa);
            }
            
            pedido.setTaxaGarcom(taxaGarcom);
            pedido.setStatus(StatusPedido.FINALIZADO);
            pedidoRepository.save(pedido);
        }
        return "redirect:/pedidos";
    }
} 