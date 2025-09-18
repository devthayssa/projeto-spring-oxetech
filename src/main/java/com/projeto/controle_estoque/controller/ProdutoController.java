package com.projeto.controle_estoque.controller;

import com.projeto.controle_estoque.model.Categoria;
import com.projeto.controle_estoque.model.Produto;
import com.projeto.controle_estoque.repository.ProdutoRepository;
import com.projeto.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os produtos
    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Cadastrar novo produto com validação
    @PostMapping
    public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody Produto produto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> erros = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(erros);
        }
        return ResponseEntity.ok(produtoRepository.save(produto));
    }

    // Buscar por nome e/ou categoria
    @GetMapping("/buscar")
    public List<Produto> buscarProdutos(@RequestParam(required = false) String nome,
                                        @RequestParam(required = false) Categoria categoria) {
        if (nome != null && categoria != null) {
            return produtoRepository.findByNomeContainingIgnoreCaseAndCategoria(nome, categoria);
        } else if (nome != null) {
            return produtoRepository.findByNomeContainingIgnoreCase(nome);
        } else if (categoria != null) {
            return produtoRepository.findByCategoria(categoria);
        } else {
            return produtoRepository.findAll();
        }
    }

    // Listar produtos com estoque abaixo do mínimo
    @GetMapping("/estoque-baixo")
    public List<Produto> listarEstoqueBaixo() {
        return produtoRepository.findProdutosComEstoqueBaixo();
    }

    // Listar produtos com validade próxima
    @GetMapping("/validade-proxima")
    public ResponseEntity<List<Produto>> alertaValidade(@RequestParam(defaultValue = "7") int dias) {
        List<Produto> produtos = produtoService.produtosComValidadeProxima(dias);
        return ResponseEntity.ok(produtos);
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        return produtoRepository.findById(id).map(produto -> {
            produto.setNome(produtoAtualizado.getNome());
            produto.setCategoria(produtoAtualizado.getCategoria());
            produto.setUnidadeMedida(produtoAtualizado.getUnidadeMedida());
            produto.setFornecedor(produtoAtualizado.getFornecedor());
            produto.setCodigoBarras(produtoAtualizado.getCodigoBarras());
            produto.setQuantidadeAtual(produtoAtualizado.getQuantidadeAtual());
            produto.setEstoqueMinimo(produtoAtualizado.getEstoqueMinimo());
            produto.setEstoqueMaximo(produtoAtualizado.getEstoqueMaximo());
            produto.setPreco(produtoAtualizado.getPreco());
            produto.setDataFabricacao(produtoAtualizado.getDataFabricacao());
            produto.setDataValidade(produtoAtualizado.getDataValidade());
            produtoRepository.save(produto);
            return ResponseEntity.ok(produto);
        }).orElse(ResponseEntity.notFound().build());
    }
}

