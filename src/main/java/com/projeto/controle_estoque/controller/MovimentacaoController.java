package com.projeto.controle_estoque.controller;

import com.projeto.controle_estoque.model.Movimentacao;
import com.projeto.controle_estoque.repository.MovimentacaoRepository;
import com.projeto.controle_estoque.repository.ProdutoRepository;
import com.projeto.controle_estoque.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Movimentacao movimentacao) {
        var produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        var usuario = usuarioRepository.findById(movimentacao.getUsuarioResponsavel().getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        movimentacao.setProduto(produto);
        movimentacao.setUsuarioResponsavel(usuario);
        movimentacaoRepository.save(movimentacao);

        return ResponseEntity.ok("Movimentação registrada com sucesso");
    }
}


