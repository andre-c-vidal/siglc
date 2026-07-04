package org.example.siglc.application;

import org.example.siglc.model.dto.FuncionarioDTO;

public class Contexto {
    private static FuncionarioDTO funcionarioLogado;

    public static void setFuncionarioLogado(FuncionarioDTO funcionarioLogado) {
        if (funcionarioLogado != null) Contexto.funcionarioLogado = funcionarioLogado;
        else throw new IllegalArgumentException("FuncionarioLogado invalido");
    }

    public static FuncionarioDTO getFuncionarioLogado() {
        return funcionarioLogado;
    }

    public static void sairLogin() {
        Contexto.funcionarioLogado = null;
    }
}
