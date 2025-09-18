package com.projeto.controle_estoque.repository;

import com.projeto.controle_estoque.model.Categoria;
import com.projeto.controle_estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoria(Categoria categoria);
    List<Produto> findByNomeContainingIgnoreCaseAndCategoria(String nome, Categoria categoria);
    @Query("SELECT p FROM Produto p WHERE p.quantidadeAtual < p.estoqueMinimo")
    List<Produto> findProdutosComEstoqueBaixo();
    @Query("SELECT p FROM Produto p WHERE p.dataValidade BETWEEN :hoje AND :limite")
    List<Produto> findProdutosComValidadeProxima(java.time.LocalDate hoje, java.time.LocalDate limite);
    




}
