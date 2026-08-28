package org.example.siglc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String categoria;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade_em_estoque")
    private int quantidadeEmEstoque;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    public void setId(Integer id) {
        if (id != null && id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) this.nome = nome;
        else throw new IllegalArgumentException("Nome invalido");
    }

    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.isBlank()) this.categoria = categoria;
        else throw new IllegalArgumentException("Categoria invalido");
    }

    public void setPreco(BigDecimal preco) {
        if (preco != null && preco.compareTo(BigDecimal.ZERO) > 0) this.preco = preco;
        else throw new IllegalArgumentException("Preco invalido");
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque >= 0) this.quantidadeEmEstoque = quantidadeEmEstoque;
        else throw new IllegalArgumentException("QuantidadeEmEstoque invalido");
    }

    public void setFornecedor(Fornecedor fornecedor) {
        if (fornecedor != null) this.fornecedor = fornecedor;
        else throw new IllegalArgumentException("Fornecedor invalido");
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }
}
