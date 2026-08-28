package org.example.siglc;

import javafx.application.Application;
import javafx.stage.Stage;

import org.example.siglc.controller.ControllerFactory;
import org.example.siglc.dao.FuncionarioDAO;
import org.example.siglc.dao.FuncionarioDAOHibernate;
import org.example.siglc.service.AutenticacaoService;
import org.example.siglc.util.Cena;
import org.example.siglc.util.GerenciadorDeCenas;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOHibernate();

        AutenticacaoService autenticacaoService = new AutenticacaoService(funcionarioDAO);

        ControllerFactory controllerFactory = new ControllerFactory(autenticacaoService);

        GerenciadorDeCenas.setControllerFactory(controllerFactory);
        GerenciadorDeCenas.setStage(stage);
        GerenciadorDeCenas.alterarCena(Cena.LOGIN);

        stage.setTitle("SIGLC");
        stage.setResizable(false);
        stage.show();
    }
}
