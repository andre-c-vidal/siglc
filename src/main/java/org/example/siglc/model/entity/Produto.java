package org.example.siglc.model.entity;

import java.math.BigDecimal;

public class Produto {
    private int id;
    private String nome;
    private String categoria;
    private BigDecimal preco;
    private int quantidadeEmEstoque;
    private int fornecedorId;

    public Produto(int id, String nome, String categoria, BigDecimal preco, int quantidadeEmEstoque, int fornecedorId) {
        setId(id);
        setNome(nome);
        setCategoria(categoria);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
        setFornecedorId(fornecedorId);
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
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

    public void setFornecedorId(int fornecedorId) {
        if (fornecedorId >= 0) this.fornecedorId = fornecedorId;
        else throw new IllegalArgumentException("Fornecedor invalido");
    }

    public int getId() {
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

    public int getFornecedorId() {
        return fornecedorId;
    }
}
