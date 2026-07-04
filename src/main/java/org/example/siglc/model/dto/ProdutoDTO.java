package org.example.siglc.model.dto;

public record ProdutoDTO(
        String codigo,
        String produto,
        String categoria,
        String preco,
        String fornecedor,
        String estoque,
        String status
) {}
