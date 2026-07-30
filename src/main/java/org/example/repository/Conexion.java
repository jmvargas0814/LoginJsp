package org.example.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

public class Conexion {

    private static final Logger logger =
            Logger.getLogger(Conexion.class.getName());

    private static final String URL = "jdbc:mysql://localhost:3306/PRUEBA_JSP";
    private static final String USER = "root";
    private static final String PASS = "123456";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            logger.severe("Error en la conexion: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
