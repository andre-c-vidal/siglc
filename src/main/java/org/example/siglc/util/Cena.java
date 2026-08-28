package org.example.siglc.util;

public enum Cena {
    LOGIN("/org/example/siglc/view/LoginView.fxml"),
    TELA_INICIAL("/org/example/siglc/view/TelaInicialView.fxml");

    public final String caminho;

    Cena(String caminho) {
        this.caminho = caminho;
    }
}
