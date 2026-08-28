package org.example.siglc.dao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.example.siglc.model.entity.Funcionario;
import org.example.siglc.util.HibernateUtil;

public class FuncionarioDAOHibernate implements FuncionarioDAO {
    @Override
    public void salvar(Funcionario funcionario) {
        Objects.requireNonNull(funcionario, "Funcionario nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(funcionario);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        Objects.requireNonNull(funcionario, "Funcionario nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Funcionario existent = session.find(Funcionario.class, funcionario.getId());
                if (existent == null) throw new DAOException("Funcionario nao encontrado");
                existent.setNome(funcionario.getNome());
                existent.setCpf(funcionario.getCpf());
                existent.setLogin(funcionario.getLogin());
                existent.setSenhaHash(funcionario.getSenhaHash());
                existent.setCargo(funcionario.getCargo());
                existent.setAtivo(funcionario.isAtivo());
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public void remover(Funcionario funcionario) {
        Objects.requireNonNull(funcionario, "Funcionario nao pode ser null");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Funcionario existente = session.find(Funcionario.class, funcionario.getId());
                if (existente == null) throw new DAOException("Funcionario nao encontrado");
                session.remove(existente);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) transaction.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<Funcionario> listar() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Funcionario", Funcionario.class).getResultList();
        }
    }

    @Override
    public Optional<Funcionario> buscarPorLogin(String login) {
        if (login == null || login.isBlank()) throw new IllegalArgumentException("Login invalido");
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session
                    .createQuery("FROM Funcionario WHERE login = :login", Funcionario.class)
                    .setParameter("login", login)
                    .uniqueResultOptional();
        }
    }
}
