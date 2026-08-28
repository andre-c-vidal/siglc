package org.example.siglc.dao;

import java.util.List;

public interface DAO <T> {
    void salvar(T entidade);
    void atualizar(T entidade);
    void remover(T entidade);
    List<T> listar();
}
