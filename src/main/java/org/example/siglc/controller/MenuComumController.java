package org.example.siglc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.example.siglc.application.Contexto;
import org.example.siglc.application.GerenteDeCenas;

public class MenuComumController {
    @FXML private Label textoNome;
    @FXML private Label textoFuncao;

    @FXML
    protected void initialize() {
        textoNome.setText(Contexto.getFuncionarioLogado().nome());
        textoFuncao.setText("Funcionário");
    }

    @FXML
    private void botaoInicioPressionado() {
        GerenteDeCenas.alterarCena("TelaInicial");
    }

    @FXML
    private void botaoProdutosPressionado() {
        GerenteDeCenas.alterarCena("Produtos");
    }

    @FXML
    private void botaoVendasPressionado() {
        GerenteDeCenas.alterarCena("Vendas");
    }

    @FXML
    private void botaoReestoquesPressionado() {
        GerenteDeCenas.alterarCena("Reestoques");
    }

    @FXML
    private void botaoRelatoriosPressionado() {
        GerenteDeCenas.alterarCena("Relatorios");
    }

    @FXML
    private void botaoFornecedoresPressionado() {
        GerenteDeCenas.alterarCena("Fornecedores");
    }

    @FXML
    private void botaoSairPressionado() {
        Contexto.sairLogin();
        GerenteDeCenas.alterarCena("Login");
    }
}
