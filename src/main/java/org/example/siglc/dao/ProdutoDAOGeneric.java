package org.example.siglc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.example.siglc.model.entity.Produto;

public class ProdutoDAOGeneric implements ProdutoDAO {
    private static final String SQL_INSERIR = "INSERT INTO produto (nome, categoria, preco, quantidade_em_estoque, fornecedor_id) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_ATUALIZAR = "UPDATE produto SET nome = ?, categoria = ?, preco = ?, quantidade_em_estoque = ?, fornecedor_id = ? WHERE id = ?;";
    private static final String SQL_DELETAR = "DELETE FROM produto WHERE id = ?;";
    private static final String SQL_LISTAR = "SELECT id, nome, categoria, preco, quantidade_em_estoque, fornecedor_id FROM produto;";

    private final Connection conexao;

    private Produto mapear(ResultSet rs) throws SQLException {
        return new Produto(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("categoria"),
                rs.getBigDecimal("preco"),
                rs.getInt("quantidade_em_estoque"),
                rs.getInt("fornecedor_id")
        );
    }

    public ProdutoDAOGeneric(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) this.conexao = conexao;
            else throw new IllegalArgumentException("Conexao invalida");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void inserir(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getCategoria());
                stmt.setBigDecimal(3, produto.getPreco());
                stmt.setInt(4, produto.getQuantidadeEmEstoque());
                stmt.setInt(5, produto.getFornecedorId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) throw new SQLException("Nenhuma linha alterada");
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) produto.setId(rs.getInt(1));
                    else throw new SQLException("Erro ao ler o ID gerado");
                }

            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void atualizar(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_ATUALIZAR)) {
                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getCategoria());
                stmt.setBigDecimal(3, produto.getPreco());
                stmt.setInt(4, produto.getQuantidadeEmEstoque());
                stmt.setInt(5, produto.getFornecedorId());
                stmt.setInt(6, produto.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) throw new SQLException("Nenhuma linha alterada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deletar(Produto produto) {
        if (produto == null) throw new IllegalArgumentException("Produto invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETAR)) {
                stmt.setInt(1, produto.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) throw new SQLException("Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Produto> listar() {
        try {
            List<Produto> lista = new ArrayList<>();
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_LISTAR)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        lista.add(mapear(rs));
                    }
                }
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
