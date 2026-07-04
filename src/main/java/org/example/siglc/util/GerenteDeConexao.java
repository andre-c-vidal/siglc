package org.example.siglc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class GerenteDeConexao {
    public static Connection abrirConexao() throws SQLException {
        Dotenv env = Dotenv.load();
        String url = env.get("DB_URL");
        String usuario = env.get("DB_USER");
        String senha = env.get("DB_PASSWORD");
        if (url == null || usuario == null || senha == null)
            throw new IllegalStateException("Erro lendo variaveis de ambiente");
        return DriverManager.getConnection(url, usuario, senha);
    }
}
