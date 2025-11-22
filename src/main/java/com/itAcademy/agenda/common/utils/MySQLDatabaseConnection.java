package com.itAcademy.agenda.common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDatabaseConnection {
    private static Connection connection;

    private void DatabaseConnection() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/project_db", "project_user", "project_pass"
                );
            } catch (SQLException e) {
                throw new RuntimeException("Error connecting to data base", e);
            }
        }
        return connection;
    }
}
