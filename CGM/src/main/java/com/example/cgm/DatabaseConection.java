package com.example.cgm;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "desmodb";
        String databaseUser = "root";
        String databasePassword = "Root";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

        return databaseLink;
    }
}
