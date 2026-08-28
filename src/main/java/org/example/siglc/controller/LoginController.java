package org.example.siglc.controller;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import org.example.siglc.service.AutenticacaoService;
import org.example.siglc.util.Cena;
import org.example.siglc.util.GerenciadorDeCenas;

public class LoginController {
    private final AutenticacaoService autenticacaoService;

    @FXML private TextField textoLogin;
    @FXML private PasswordField textoSenha;

    public LoginController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = Objects.requireNonNull(autenticacaoService);
    }

    @FXML
    private void botaoEntrarPressionado() {
        String login = textoLogin.getText();
        String senha = textoSenha.getText();
        if (login.isBlank()) {
            mostrarAlerta(AlertType.ERROR, "Login invalido", "Por favor digite um login valido");
            textoLogin.clear();
            textoLogin.requestFocus();
            return;
        }
        if (senha.isBlank()) {
            mostrarAlerta(AlertType.ERROR, "Senha invalida", "Por favor digite uma senha valida");
            textoSenha.clear();
            textoSenha.requestFocus();
            return;
        }
        boolean loginSucesso = autenticacaoService.login(login, senha);
        if (loginSucesso) GerenciadorDeCenas.alterarCena(Cena.TELA_INICIAL);
        else mostrarAlerta(AlertType.WARNING, "Login invalido", "Login ou senha incorretos");
    }

    private static void mostrarAlerta(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(titulo);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
