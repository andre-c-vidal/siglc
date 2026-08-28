package org.example.siglc.service;

import java.util.Objects;
import java.util.Optional;

import org.example.siglc.dao.FuncionarioDAO;
import org.example.siglc.model.entity.Funcionario;
import org.mindrot.jbcrypt.BCrypt;

import org.example.siglc.model.dto.FuncionarioDTO;
import org.example.siglc.util.Contexto;

public class AutenticacaoService {
    private final FuncionarioDAO funcionarioDAO;

    public AutenticacaoService(FuncionarioDAO funcionarioDAO) {
        this.funcionarioDAO = Objects.requireNonNull(funcionarioDAO);
    }

    public boolean login(String login, String senha) {
        if (login == null || login.isBlank()) return false;
        if (senha == null || senha.isBlank()) return false;
        Optional<Funcionario> funcionarioOptional = funcionarioDAO.buscarPorLogin(login);
        if (funcionarioOptional.isEmpty()) return false;
        Funcionario funcionario = funcionarioOptional.get();
        if (!BCrypt.checkpw(senha, funcionario.getSenhaHash())) return false;
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO(funcionario.getNome(), funcionario.getCargo());
        Contexto.setFuncionarioLogado(funcionarioDTO);
        return true;
    }
}
