package org.example.siglc.model.entity;

import java.math.BigDecimal;

public class ProdutoEmVenda {
    private int vendaId;
    private int produtoId;
    private BigDecimal precoUnitario;
    private int quantidade;

    public ProdutoEmVenda(int vendaId, int produtoId, BigDecimal precoUnitario, int quantidade) {
        setVendaId(vendaId);
        setProdutoId(produtoId);
        setPrecoUnitario(precoUnitario);
        setQuantidade(quantidade);
    }

    public void setVendaId(int vendaId) {
        if (vendaId >= 0) this.vendaId = vendaId;
        else throw new IllegalArgumentException("VendaId invalido");
    }

    public void setProdutoId(int produtoId) {
        if (produtoId >= 0) this.produtoId = produtoId;
        else throw new IllegalArgumentException("ProdutoId invalido");
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        if (precoUnitario != null && precoUnitario.compareTo(BigDecimal.ZERO) > 0) this.precoUnitario = precoUnitario;
        else throw new IllegalArgumentException("PrecoUnitario invalido");
    }

    public void setQuantidade(int quantidade) {
        if (quantidade >= 0) this.quantidade = quantidade;
        else throw new IllegalArgumentException("Quantidade invalido");
    }

    public int getVendaId() {
        return vendaId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
