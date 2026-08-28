package org.example.siglc.dao;

import java.util.List;
import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.example.siglc.model.entity.Fornecedor;
import org.example.siglc.model.entity.Produto;
import org.example.siglc.util.HibernateUtil;

public class ProdutoDAOHibernate implements ProdutoDAO {
    @Override
    public void salvar(Produto produto) {
        Objects.requireNonNull(produto, "Produto nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(produto);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void atualizar(Produto produto) {
        Objects.requireNonNull(produto, "Produto nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Produto existent = session.find(Produto.class, produto.getId());
                if (existent == null) throw new DAOException("Produto nao encontrado");
                existent.setNome(produto.getNome());
                existent.setCategoria(produto.getCategoria());
                existent.setPreco(produto.getPreco());
                existent.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
                Fornecedor fornecedor = session.find(Fornecedor.class, produto.getFornecedor().getId());
                if (fornecedor == null) throw new DAOException("Fornecedor nao encontrado");
                existent.setFornecedor(fornecedor); // What happens here?
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void remover(Produto produto) {
        Objects.requireNonNull(produto, "Produto nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Produto existente = session.find(Produto.class, produto.getId());
                if (existente == null) throw new DAOException("Produto fornecido nao existe no banco de dados");
                session.remove(existente);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Produto> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Produto", Produto.class).getResultList();
        }
    }
}
