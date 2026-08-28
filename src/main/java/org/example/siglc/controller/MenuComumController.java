package org.example.siglc.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.example.siglc.util.Cena;
import org.example.siglc.util.Contexto;
import org.example.siglc.util.GerenciadorDeCenas;

public class MenuComumController {
    @FXML private Label textoNome;
    @FXML private Label textoFuncao;

    @FXML
    protected void initialize() {
        textoNome.setText(Contexto.getFuncionarioLogado().nome());
        textoFuncao.setText(Contexto.getFuncionarioLogado().cargo());
    }

    @FXML
    private void botaoInicioPressionado() {
        GerenciadorDeCenas.alterarCena(Cena.TELA_INICIAL);
    }

    @FXML
    private void botaoProdutosPressionado() {

    }

    @FXML
    private void botaoVendasPressionado() {

    }

    @FXML
    private void botaoReestoquesPressionado() {

    }

    @FXML
    private void botaoRelatoriosPressionado() {

    }

    @FXML
    private void botaoFornecedoresPressionado() {

    }

    @FXML
    private void botaoSairPressionado() {
        Contexto.sairLogin();
        GerenciadorDeCenas.alterarCena(Cena.LOGIN);
    }
}
