package com.example.madera.repository;

import com.example.madera.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de banco de dados relacionadas à entidade Mesa.
 * Fornece métodos para persistência e consulta de mesas.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
// Repositório para operações de banco de dados relacionadas à entidade Mesa
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
} 