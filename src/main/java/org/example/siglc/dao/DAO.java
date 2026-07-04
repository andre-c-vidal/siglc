package org.example.siglc.dao;

import java.util.List;

public interface DAO <T> {
    void inserir(T entidade);
    void atualizar(T entidade);
    void deletar(T entidade);
    List<T> listar();
}
