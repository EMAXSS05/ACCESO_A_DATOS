package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
        "DNI_Cliente char(9) not null,\n"+
        "primary key(ID),"+
        "constraint fk_Cliente_pedido foreign key(DNI_Cliente) references Cliente(DNI) ON UPDATE CASCADE ON DELETE CASCADE)"
        
        );
       
        }
        catch(Exception e){

        }
    }


    

    void insertarPedido(int id, Date fecha, String dni_cliente){

        try {
            PreparedStatement stmt= conn.prepareStatement("insert into Pedido(ID,Fecha,DNI_Cliente) values(?,?,?)");
            stmt.setInt(1, id);
            stmt.setDate(2, fecha);
            stmt.setString(3, dni_cliente);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo insertar el pedido");
        }
           
    }

}
