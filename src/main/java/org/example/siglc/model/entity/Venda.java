package org.example.siglc.model.entity;

import java.time.LocalDateTime;

public class Venda {
    private int id;
    private int funcionarioId;
    private LocalDateTime dataHora;
    private String formaPagamento;

    public Venda(int funcionarioId, LocalDateTime dataHora, String formaPagamento) {
        setFuncionarioId(funcionarioId);
        setDataHora(dataHora);
    }

    public void setId(int id) {
        if (id >= 0) this.id = id;
        else throw new IllegalArgumentException("ID invalido");
    }

    public void setFuncionarioId(int funcionarioId) {
        if (funcionarioId >= 0) this.funcionarioId = funcionarioId;
        else throw new IllegalArgumentException("Funcionario invalido");
    }

    public void setDataHora(LocalDateTime dataHora) {
        if (dataHora != null) this.dataHora = dataHora;
        else throw new IllegalArgumentException("dataHora invalido");
    }

    public void setFormaPagamento(String formaPagamento) {
        if (formaPagamento != null && !formaPagamento.isBlank()) this.formaPagamento = formaPagamento;
        else throw new IllegalArgumentException("FormaPagamento invalido");
    }

    public int getId() {
        return id;
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }
}
