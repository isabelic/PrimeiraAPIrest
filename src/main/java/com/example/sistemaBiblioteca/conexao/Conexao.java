package com.example.sistemaBiblioteca.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3355/BIBLIOTECA?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "mysqlPW";

    public static Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
