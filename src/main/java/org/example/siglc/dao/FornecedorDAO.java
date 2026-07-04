package org.example.siglc.dao;

import java.util.Optional;

import org.example.siglc.model.entity.Fornecedor;

public interface FornecedorDAO extends DAO<Fornecedor> {
    Optional<Fornecedor> buscarPorId(int id);
}
