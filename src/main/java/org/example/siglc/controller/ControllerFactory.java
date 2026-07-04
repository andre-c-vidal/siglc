package org.example.siglc.controller;

import java.util.Objects;

import javafx.util.Callback;

import org.example.siglc.service.AutenticacaoService;
import org.example.siglc.service.ProdutoService;

public class ControllerFactory implements Callback<Class<?>, Object> {
    private final AutenticacaoService autenticacaoService;
    private final ProdutoService produtoService;

    public ControllerFactory(AutenticacaoService autenticacaoService, ProdutoService produtoService) {
        this.autenticacaoService = Objects.requireNonNull(autenticacaoService);
        this.produtoService = Objects.requireNonNull(produtoService);
    }

    public Object call(Class<?> controllerClass) {
        switch (controllerClass.getSimpleName()) {
            case "LoginController":
                return new LoginController(autenticacaoService);
            case "TelaInicialController":
                return new TelaInicialController();
            case "ProdutosController":
                return new ProdutosController(produtoService);
            case "NovoProdutoController":
                return new NovoProdutoController();
            default:
                return null;
        }
    }
}
