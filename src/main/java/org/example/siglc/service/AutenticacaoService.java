package org.example.siglc.service;

import java.util.Optional;

import org.example.siglc.model.entity.Funcionario;
import org.mindrot.jbcrypt.BCrypt;

import org.example.siglc.dao.FuncionarioDAO;
import org.example.siglc.model.dto.FuncionarioDTO;

public class AutenticacaoService {
    private final FuncionarioDAO funcionarioDAO;

    public AutenticacaoService(FuncionarioDAO funcionarioDAO) {
        if (funcionarioDAO != null) this.funcionarioDAO = funcionarioDAO;
        else throw new IllegalArgumentException("FuncionarioDAO invalido");
    }

    public Optional<FuncionarioDTO> tentarLogin(String login, String senha) {
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login invalido");
        if (senha == null || senha.isBlank()) throw new IllegalArgumentException("Senha invalida");
        return funcionarioDAO.buscarPorLogin(login)
                .filter(Funcionario::isAtivo)
                .filter(f -> BCrypt.checkpw(senha, f.getSenhaHash()))
                .map(f -> new FuncionarioDTO(f.getId(), f.getNome()));
    }
}
