package com.projeto.controle_estoque.repository;

import com.projeto.controle_estoque.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByProdutoId(Long produtoId);
    List<Movimentacao> findByDataBetween(LocalDate inicio, LocalDate fim);
}

