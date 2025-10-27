package com.example;

import java.sql.Connection;

public interface DBConnection {
    Connection getConnection();
}