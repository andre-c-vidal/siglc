package org.example.siglc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false, length = 30)
    private String login;

    @Column(name = "senha_hash", nullable = false, length = 60)
    private String senhaHash;

    @Column(nullable = false)
    private Boolean ativo;

    @Column(nullable = false, length = 100)
    private String cargo;

    public void setId(Integer id) {
        if (id != null && id >= 0) this.id = id;
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

    public void setAtivo(Boolean ativo) {
        if (ativo != null) this.ativo = ativo;
        else throw new IllegalArgumentException("Boolean nao pode ser nulo");
    }

    public void setCargo(String cargo) {
        if (cargo != null && !cargo.isBlank()) this.cargo = cargo;
        else throw new IllegalArgumentException("Cargo invalido");
    }

    public Integer getId() {
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

    public Boolean isAtivo() {
        return ativo;
    }

    public String getCargo() {
        return cargo;
    }
}
