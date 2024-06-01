package com.yspjt.dbafe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521/xe";
        String username = "DBAFE";
        String password = "1";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to Oracle database!");
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
