package org.example.siglc.controller;

import java.util.Objects;

import javafx.util.Callback;

import org.example.siglc.service.AutenticacaoService;

public class ControllerFactory implements Callback<Class<?>, Object> {
    private final AutenticacaoService autenticacaoService;

    public ControllerFactory(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = Objects.requireNonNull(autenticacaoService);
    }

    @Override
    public Object call(Class<?> controllerClass) {
        return switch (controllerClass.getSimpleName()) {
            case "LoginController" -> new LoginController(autenticacaoService);
            case "TelaInicialController" -> new TelaInicialController();
            default -> null;
        };
    }
}
