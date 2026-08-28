package org.example.siglc.util;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.siglc.controller.ControllerFactory;

public final class GerenciadorDeCenas {
    private static Stage primaryStage;
    private static ControllerFactory controllerFactory;

    private GerenciadorDeCenas() {}

    public static void setControllerFactory(ControllerFactory controllerFactory) {
        GerenciadorDeCenas.controllerFactory = Objects.requireNonNull(controllerFactory);
    }

    public static void setStage(Stage primaryStage) {
        GerenciadorDeCenas.primaryStage = Objects.requireNonNull(primaryStage);
    }

    public static void alterarCena(Cena cena) {
        Objects.requireNonNull(cena);
        URL location = GerenciadorDeCenas.class.getResource(cena.caminho);
        FXMLLoader loader = new FXMLLoader(location);
        loader.setControllerFactory(GerenciadorDeCenas.controllerFactory);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
