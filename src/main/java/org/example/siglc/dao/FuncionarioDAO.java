package org.example.siglc.dao;

import java.util.Optional;

import org.example.siglc.model.entity.Funcionario;

public interface FuncionarioDAO extends DAO<Funcionario> {
    Optional<Funcionario> buscarPorLogin(String login);
}
