package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        "ID_Pedido int,\n"+
        "Cantidad int not null,\n"+
        "primary key(ID),\n"+
        "constraint fk_pedido_producto foreign key(ID_Pedido) references Pedido(ID) ON UPDATE CASCADE ON DELETE CASCADE)"
        );
        
     } catch (Exception e) {
        System.out.println("Error al crear la tabla Producto");
     }
    }

    void insertarProducto(int id, String descripcion, String nombre, float precio, int cantidad){
        try {
         PreparedStatement stmt= conn.prepareStatement("insert into Producto(ID,Descripcion,Nombre,Precio,Cantidad) values(?,?,?,?,?)");
         stmt.setInt(1, id);  
         stmt.setString(2, descripcion);
         stmt.setString(3, nombre);
         stmt.setFloat(4, precio);
         stmt.setInt(5, cantidad);
         stmt.executeUpdate();

        } catch (Exception e) { 
         System.out.println("No se pudo insertar el producto");
        }
    }


}
