package com.example.madera.service;

import com.example.madera.model.Mesa;
import com.example.madera.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Serviço responsável pela lógica de negócios relacionada às mesas.
 * Implementa operações CRUD e regras de negócio específicas.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;

    /**
     * Lista todas as mesas cadastradas.
     * 
     * @return Lista de todas as mesas
     */
    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    /**
     * Busca uma mesa pelo seu ID.
     * 
     * @param id ID da mesa
     * @return Mesa encontrada ou null se não existir
     */
    public Mesa buscarPorId(Long id) {
        return mesaRepository.findById(id).orElse(null);
    }

    /**
     * Salva uma nova mesa ou atualiza uma existente.
     * 
     * @param mesa Mesa a ser salva
     * @return Mesa salva
     */
    public Mesa salvar(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    /**
     * Exclui uma mesa pelo seu ID.
     * 
     * @param id ID da mesa a ser excluída
     */
    public void excluir(Long id) {
        mesaRepository.deleteById(id);
    }
} 