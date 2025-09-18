package com.projeto.controle_estoque.service;

import com.projeto.controle_estoque.model.Movimentacao;
import com.projeto.controle_estoque.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }

    public List<Movimentacao> porProduto(Long produtoId) {
        return movimentacaoRepository.findByProdutoId(produtoId);
    }

    public List<Movimentacao> porPeriodo(LocalDate inicio, LocalDate fim) {
        return movimentacaoRepository.findByDataBetween(inicio, fim);
    }
}

