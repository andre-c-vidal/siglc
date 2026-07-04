package org.example.siglc.model.entity;

public class ProdutoEmReestoque {
    private int reestoqueId;
    private int produtoId;
    private int quantidadeReestocada;

    public ProdutoEmReestoque(int reestoqueId, int produtoId, int quantidadeReestocada) {
        setReestoqueId(reestoqueId);
        setProdutoId(produtoId);
        setQuantidadeReestocada(quantidadeReestocada);
    }

    public void setReestoqueId(int reestoqueId) {
        if (reestoqueId >= 0) this.reestoqueId = reestoqueId;
        else throw new IllegalArgumentException("reestoqueId invalido");
    }

    public void setProdutoId(int produtoId) {
        if (produtoId >= 0) this.produtoId = produtoId;
        else throw new IllegalArgumentException("produtoId invalido");
    }

    public void setQuantidadeReestocada(int quantidadeReestocada) {
        if (quantidadeReestocada > 0) this.quantidadeReestocada = quantidadeReestocada;
        else throw new IllegalArgumentException("quantidadeReestocada invalido");
    }

    public int getReestoqueId() {
        return reestoqueId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public int getQuantidadeReestocada() {
        return quantidadeReestocada;
    }
}
