package com.example;

import java.sql.Connection;
import java.sql.Statement;

public class GestorProductos {
  Connection conn;
    public GestorProductos(Connection conn){
      this.conn=conn;
     try {
        Statement stmt= conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Producto(\n"+
        "ID int not null,\n"+
        "Descripcion varchar(35),\n"+
        "Nombre varchar(20) not null,\n"+
        "Precio float not null,\n"+
        "primary key(ID)),\n"
        );
     } catch (Exception e) {
        System.out.println("Error al crear la tabla Producto");
     }
    }


}
