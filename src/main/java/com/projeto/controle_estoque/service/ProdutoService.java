package com.projeto.controle_estoque.service;

import com.projeto.controle_estoque.model.Produto;
import com.projeto.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> produtosComValidadeProxima(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate limite = hoje.plusDays(dias);
        return produtoRepository.findProdutosComValidadeProxima(hoje, limite);
    }

    // outros métodos que você já tem...
}




