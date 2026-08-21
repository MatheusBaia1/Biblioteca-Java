package com.matheus.biblioteca.repository;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

        public static Connection getConexao() throws SQLException, IOException {
            Properties props = new Properties();
            props.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties"));

            String url = props.getProperty("db.url");
            String usuario = props.getProperty("db.usuario");
            String senha = props.getProperty("db.senha");

            return DriverManager.getConnection(url, usuario, senha);
        }
}
