package org.example.siglc.application;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.siglc.controller.ControllerFactory;

public class GerenteDeCenas {
    private static final String FXML_BASE_PATH = "/view/";
    private static final String FXML_SUFFIX = "View.fxml";

    private static Stage primaryStage;
    private static ControllerFactory controllerFactory;

    public static void setControllerFactory(ControllerFactory controllerFactory) {
        if (controllerFactory != null) GerenteDeCenas.controllerFactory = controllerFactory;
        else throw new IllegalArgumentException("ControllerFactory invalido");
    }

    public static void setStage(Stage primaryStage) {
        if (primaryStage != null) GerenteDeCenas.primaryStage = primaryStage;
        else throw new IllegalArgumentException("Stage invalido");
        primaryStage.setResizable(false);
    }

    private static void carregarCena(FXMLLoader loader) {
        try {
            Scene cena = new Scene(loader.load());
            primaryStage.setScene(cena);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static FXMLLoader criarLoader(String nome) {
        String fxmlPath = FXML_BASE_PATH + nome + FXML_SUFFIX;
        URL fxmlURL = GerenteDeCenas.class.getResource(fxmlPath);
        if (fxmlURL == null) throw new IllegalArgumentException("Arquivo FXML não encontrado");
        return new FXMLLoader(fxmlURL);
    }

    public static void alterarCena(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome invalido");
        FXMLLoader loader = criarLoader(nome);
        loader.setControllerFactory(controllerFactory);
        carregarCena(loader);
    }
}
