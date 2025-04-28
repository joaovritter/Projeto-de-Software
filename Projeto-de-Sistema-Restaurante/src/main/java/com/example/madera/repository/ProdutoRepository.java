package com.example.madera.repository;

import com.example.madera.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para operações de banco de dados relacionadas à entidade Produto.
 * Fornece métodos para persistência e consulta de produtos.
 * 
 * @author Equipe Madera
 * @version 1.0
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
} 