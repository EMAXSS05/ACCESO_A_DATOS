package com.example;


import java.sql.Connection;
import java.sql.Statement;

public class GestorClientes {
    Connection conn;
    public GestorClientes(Connection conn){
        this.conn=conn;
        try {
            Statement stmt= conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Cliente(\n"+
            "DNI char(9) not null,\n"+
            "NOMBRE varchar(30) not null,\n"+
            "ID_Pedido int not null,\n"+
            "primary key(DNI))"
           
            );
        } catch (Exception e) {
            System.out.println("Error al crear la tabla Clientes");
        }
    }

  
}
