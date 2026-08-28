package org.example.siglc.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.siglc.model.entity.Fornecedor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.example.siglc.model.entity.Funcionario;
import org.example.siglc.model.entity.Produto;

public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Dotenv dotenv = Dotenv.load();
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.url", dotenv.get("DB_URL"));
        configuration.setProperty("hibernate.connection.username", dotenv.get("DB_USERNAME"));
        configuration.setProperty("hibernate.connection.password", dotenv.get("DB_PASSWORD"));
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.highlight_sql", "true");
        configuration.addAnnotatedClass(Fornecedor.class);
        configuration.addAnnotatedClass(Funcionario.class);
        configuration.addAnnotatedClass(Produto.class);
        return configuration.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
