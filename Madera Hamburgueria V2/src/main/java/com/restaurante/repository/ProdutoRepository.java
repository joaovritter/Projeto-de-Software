package com.restaurante.repository;

import com.restaurante.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByDisponivelTrue();
    List<Produto> findByCategoria(Produto.CategoriaProduto categoria);
} 