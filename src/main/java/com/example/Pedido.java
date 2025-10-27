package com.example;

import java.sql.Connection;
import java.sql.Statement;

public class Pedido {
   
    Connection conn;
    public Pedido(Connection conn){
        this.conn=conn;
        try{
        Statement stmt= conn.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Pedido(\n"+
        "ID int not null,\n"+
        "Fecha datetime not null,\n"+
        "ID_Producto int not null,\n"+
        "ID_Cliente int not null,\n"+
        "primary key(ID))\n"+
        "constraint fk_cliente_pedido foreign key(ID_Pedido) references Pedido(ID)) "
        
        );
        }
        catch(Exception e){

        }
    }

}
