package org.example.siglc.model.entity;

public class Funcionario {
    private int id;
    private String nome;
    private String cpf;
    private String login;
    private String senhaHash;
    private boolean ativo;

    public Funcionario(int id, String nome, String cpf, String login, String senhaHash, boolean ativo) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setLogin(login);
        setSenhaHash(senhaHash);
        setAtivo(ativo);
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) this.nome = nome;
        else throw new IllegalArgumentException("Nome invalido");
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isBlank()) this.cpf = cpf;
        else throw new IllegalArgumentException("CPF invalido");
    }

    public void setLogin(String login) {
        if (login != null && !login.isBlank()) this.login = login;
        else throw new IllegalArgumentException("Login invalido");
    }

    public void setSenhaHash(String senhaHash) {
        if (senhaHash != null && !senhaHash.isBlank()) this.senhaHash = senhaHash;
        else throw new IllegalArgumentException("Senha invalido");
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getLogin() {
        return login;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
