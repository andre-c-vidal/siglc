package org.example.siglc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.example.siglc.dao.FornecedorDAO;
import org.example.siglc.dao.ProdutoDAO;
import org.example.siglc.model.dto.ProdutoDTO;
import org.example.siglc.model.entity.Fornecedor;
import org.example.siglc.model.entity.Produto;

public class ProdutoService {
    private final FornecedorDAO fornecedorDAO;
    private final ProdutoDAO produtoDAO;

    public ProdutoService(FornecedorDAO fornecedorDAO, ProdutoDAO produtoDAO) {
        if (fornecedorDAO != null) this.fornecedorDAO = fornecedorDAO;
        else throw new IllegalArgumentException("FornecedorDAO invalido");
        if (produtoDAO != null) this.produtoDAO = produtoDAO;
        else throw new IllegalArgumentException("ProdutoDAO invalido");
    }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoDAO.listar();
        List<ProdutoDTO> produtosDTO = new ArrayList<>();
        for (Produto produto : produtos) {
            Optional<Fornecedor> fornecedorOptional = fornecedorDAO.buscarPorId(produto.getFornecedorId());
            if (fornecedorOptional.isEmpty()) throw new IllegalStateException("Produto sem fornecedor correspondente");
            Fornecedor fornecedor = fornecedorOptional.get();
            String status;
            int quantidadeEmEstoque = produto.getQuantidadeEmEstoque();
            if (quantidadeEmEstoque == 0) status = "ESGOTADO";
            else if (quantidadeEmEstoque < 10) status = "ESTOQUE BAIXO";
            else status = "OK";
            ProdutoDTO produtoDTO = new ProdutoDTO(
                    String.format("%03d", produto.getId()),
                    produto.getNome(),
                    produto.getCategoria(),
                    String.format("R$ %.2f", produto.getPreco()),
                    fornecedor.getNome(),
                    String.format("%d un", produto.getQuantidadeEmEstoque()),
                    status
            );
            produtosDTO.add(produtoDTO);
        }
        return produtosDTO;
    }

    public List<String> listarCategorias() {
        List<Produto> produtos = produtoDAO.listar();
        List<String> categorias = new ArrayList<>();
        for (Produto produto : produtos) {
            String categoria = produto.getCategoria();
            if (!categorias.contains(categoria))
                categorias.add(categoria);
        }
        return categorias;
    }

    public List<String> listarCategorias(List<ProdutoDTO> produtos) {
        if (produtos == null) throw new IllegalStateException("Lista de produtos invalida");
        List<String> categorias = new ArrayList<>();
        for (ProdutoDTO produto : produtos) {
            String categoria = produto.categoria();
            if (!categorias.contains(categoria))
                categorias.add(categoria);
        }
        return categorias;
    }
}
