package com.example;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {
      String URL="";
      String USER="root";
      String PASSWORD="";

      
      
      
    @Override
    public Connection getConnection() {
         URL="jdbc:mysql://localhost:3306/";
        try {
           return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            return null;
        }

    }

    
}