package com.projeto.controle_estoque.controller;

import com.projeto.controle_estoque.model.Movimentacao;
import com.projeto.controle_estoque.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<Movimentacao> listarTodas() {
        return movimentacaoService.listarTodas();
    }

    @GetMapping("/produto/{id}")
    public List<Movimentacao> porProduto(@PathVariable Long id) {
        return movimentacaoService.porProduto(id);
    }

    @GetMapping("/periodo")
    public List<Movimentacao> porPeriodo(@RequestParam LocalDate inicio,
                                         @RequestParam LocalDate fim) {
        return movimentacaoService.porPeriodo(inicio, fim);
    }
}


