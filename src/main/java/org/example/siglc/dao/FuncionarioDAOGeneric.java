package org.example.siglc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.siglc.model.entity.Funcionario;

public class FuncionarioDAOGeneric implements FuncionarioDAO {
    private static final String SQL_INSERIR = "INSERT INTO funcionario (nome, cpf, login, senha_hash, ativo) VALUES (?, ?, ?, ?, ?);";
    private static final String SQL_ATUALIZAR = "UPDATE funcionario SET nome = ?, cpf = ?, login = ?, senha_hash = ?, ativo = ? WHERE id = ?;";
    private static final String SQL_LISTAR = "SELECT id, nome, cpf, login, senha_hash, ativo FROM funcionario;";
    private static final String SQL_DELETAR = "DELETE FROM funcionario WHERE id = ?;";
    private static final String SQL_BUSCAR_POR_LOGIN = "SELECT id, nome, cpf, login, senha_hash, ativo FROM funcionario WHERE login = ?;";

    private final Connection conexao;

    private static Funcionario mapear(ResultSet rs) {
        try {
            return new Funcionario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("login"),
                    rs.getString("senha_hash"),
                    rs.getBoolean("ativo")
            );
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public FuncionarioDAOGeneric(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                this.conexao = conexao;
            } else throw new IllegalArgumentException("Conexao invalida");
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public void inserir(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("Funcionario invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCpf());
                stmt.setString(3, funcionario.getLogin());
                stmt.setString(4, funcionario.getSenhaHash());
                stmt.setBoolean(5, funcionario.isAtivo());
                stmt.executeUpdate();
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        funcionario.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void atualizar(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("Funcionario invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_ATUALIZAR)) {
                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCpf());
                stmt.setString(3, funcionario.getLogin());
                stmt.setString(4, funcionario.getSenhaHash());
                stmt.setBoolean(5, funcionario.isAtivo());
                stmt.setInt(6, funcionario.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) throw new SQLException("Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public void deletar(Funcionario funcionario) {
        if (funcionario == null) throw new IllegalArgumentException("Funcionario invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_DELETAR)) {
                stmt.setInt(1, funcionario.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas == 0) throw new SQLException("Nenhuma linha afetada");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    public List<Funcionario> listar() {
        try {
            List<Funcionario> funcionarios = new ArrayList<>();
            try (Statement stmt = conexao.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(SQL_LISTAR)) {
                    while (rs.next()) {
                        funcionarios.add(mapear(rs));
                    }
                }
            }
            return funcionarios;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Optional<Funcionario> buscarPorLogin(String login) {
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login invalido");
        try {
            try (PreparedStatement stmt = conexao.prepareStatement(SQL_BUSCAR_POR_LOGIN)) {
                stmt.setString(1, login);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) return Optional.of(FuncionarioDAOGeneric.mapear(rs));
                    else return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
