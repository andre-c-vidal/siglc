package org.example.siglc.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, length = 100)
    private String email;

    public void setId(Integer id) {
        if (id != null && id >= 0) this.id = id;
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
        else throw new IllegalArgumentException("Email invalido");
    }

    public Integer getId() {
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
