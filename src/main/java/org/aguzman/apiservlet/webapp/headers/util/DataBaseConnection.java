package org.aguzman.apiservlet.webapp.headers.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static String url = "jdbc:mysql://localhost:3306/java_curso?serverTimeZone=America/Bogota";
    private static String username = "root";
    private static String password = "admin";

    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url,username,password);
    }
}
