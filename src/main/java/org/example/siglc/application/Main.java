package org.example.siglc.application;

import java.sql.Connection;

import javafx.application.Application;
import javafx.stage.Stage;

import org.example.siglc.controller.ControllerFactory;
import org.example.siglc.dao.*;
import org.example.siglc.service.AutenticacaoService;
import org.example.siglc.service.ProdutoService;
import org.example.siglc.util.GerenteDeConexao;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Connection conexao = GerenteDeConexao.abrirConexao();
        FornecedorDAO fornecedorDAO = new FornecedorDAOGeneric(conexao);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAOGeneric(conexao);
        ProdutoDAO produtoDAO = new ProdutoDAOGeneric(conexao);

        AutenticacaoService autenticacaoService = new AutenticacaoService(funcionarioDAO);
        ProdutoService produtoService = new ProdutoService(fornecedorDAO, produtoDAO);

        ControllerFactory controllerFactory = new ControllerFactory(autenticacaoService, produtoService);

        GerenteDeCenas.setControllerFactory(controllerFactory);
        GerenteDeCenas.setStage(stage);

        GerenteDeCenas.alterarCena("Login");
        stage.setTitle("SIGLC");
        stage.show();
    }
}
