package org.example.siglc.dao;

import org.example.siglc.model.entity.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FornecedorDAOGeneric implements FornecedorDAO {
    private static final String SQL_INSERIR = "INSERT INTO fornecedor (nome, telefone, email) VALUES (?, ?, ?);";
    private static final String SQL_ATUALIZAR = "UPDATE fornecedor SET nome = ?, telefone = ?, email = ? WHERE id = ?;";
    private static final String SQL_DELETAR = "DELETE FROM fornecedor WHERE id = ?;";
    private static final String SQL_LISTAR = "SELECT id, nome, telefone, email FROM fornecedor";
    private static final String SQL_BUSCAR_POR_ID = "SELECT id, nome, telefone, email FROM fornecedor WHERE id = ?;";

    private final Connection conexao;

    private Fornecedor mapear(ResultSet rs) throws SQLException {
        return new Fornecedor(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("telefone"),
                rs.getString("email")
        );
    }

    public FornecedorDAOGeneric(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) this.conexao = conexao;
            else throw new IllegalArgumentException("Conexao invalida");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Fornecedor> buscarPorId(int id) {
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_BUSCAR_POR_ID)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(mapear(rs));
                    } else return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void inserir(Fornecedor fornecedor) {
        if (fornecedor == null) throw new IllegalStateException("Fornecedor invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, fornecedor.getNome());
                stmt.setString(2, fornecedor.getTelefone());
                stmt.setString(3, fornecedor.getEmail());
                int linhasAlteradas = stmt.executeUpdate();
                if (linhasAlteradas == 0) throw new SQLException("Nenhuma linha afetada");
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        fornecedor.setId(rs.getInt(1));
                    } else throw new SQLException("Erro lendo o ID gerado");
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void atualizar(Fornecedor fornecedor) {
        if (fornecedor == null) throw new IllegalArgumentException("Fornecedor invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_ATUALIZAR)) {
                stmt.setString(1, fornecedor.getNome());
                stmt.setString(2, fornecedor.getTelefone());
                stmt.setString(3, fornecedor.getEmail());
                stmt.setInt(4, fornecedor.getId());
                int linhasAlteradas = stmt.executeUpdate();
                if (linhasAlteradas == 0) throw new SQLException("Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void deletar(Fornecedor fornecedor) {
        if (fornecedor == null) throw new IllegalArgumentException("Fornecedor invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETAR)) {
                stmt.setInt(1, fornecedor.getId());
                int linhasAlteradas = stmt.executeUpdate();
                if (linhasAlteradas == 0) throw new SQLException("Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<Fornecedor> listar() {
        try {
            List<Fornecedor> lista = new ArrayList<>();
            try (Statement stmt = conexao.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(SQL_LISTAR)) {
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
