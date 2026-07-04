package org.example.siglc.model.entity;

public class Fornecedor {
    private int id;
    private String nome;
    private String telefone;
    private String email;

    public Fornecedor(int id, String nome, String telefone, String email) {
        setId(id);
        setNome(nome);
        setTelefone(telefone);
        setEmail(email);
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) this.nome = nome;
        else throw new IllegalArgumentException("Nome invalido");
    }

    public void setTelefone(String telefone) {
        if (telefone != null && !telefone.isBlank()) this.telefone = telefone;
        else throw new IllegalArgumentException("Telefone invalido");
    }

    public void setEmail(String email) {
        if (email != null && !email.isBlank()) this.email = email;
        else throw new IllegalArgumentException("Telefone invalido");
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }
}
