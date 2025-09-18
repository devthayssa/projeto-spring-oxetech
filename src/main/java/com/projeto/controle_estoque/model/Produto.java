package com.projeto.controle_estoque.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A categoria é obrigatória")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @NotNull(message = "A unidade de medida é obrigatória")
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @NotBlank(message = "O fornecedor é obrigatório")
    private String fornecedor;

    @NotBlank(message = "O código de barras é obrigatório")
    private String codigoBarras;

    @Min(value = 0, message = "A quantidade atual não pode ser negativa")
    private int quantidadeAtual;

    @Min(value = 1, message = "O estoque mínimo deve ser pelo menos 1")
    private int estoqueMinimo;

    @Min(value = 1, message = "O estoque máximo deve ser pelo menos 1")
    private int estoqueMaximo;

    @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A data de fabricação é obrigatória")
    private LocalDate dataFabricacao;

    @NotNull(message = "A data de validade é obrigatória")
    private LocalDate dataValidade;

    @AssertTrue(message = "A data de validade deve ser posterior à data de fabricação")
    public boolean isValidadeMaiorQueFabricacao() {
        return dataValidade != null && dataFabricacao != null && dataValidade.isAfter(dataFabricacao);
    }

    // Getters e Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public UnidadeMedida getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(UnidadeMedida unidadeMedida) { this.unidadeMedida = unidadeMedida; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public int getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(int quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }

    public int getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }

    public int getEstoqueMaximo() { return estoqueMaximo; }
    public void setEstoqueMaximo(int estoqueMaximo) { this.estoqueMaximo = estoqueMaximo; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public LocalDate getDataFabricacao() { return dataFabricacao; }
    public void setDataFabricacao(LocalDate dataFabricacao) { this.dataFabricacao = dataFabricacao; }

    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
}
