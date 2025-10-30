package com.example;


import java.sql.Connection;
import java.sql.PreparedStatement;
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
            "primary key(DNI))"
           
            );
            
        } catch (Exception e) {
            System.out.println("Error al crear la tabla Clientes");
        }
    }

    void insertarCliente(String dni, String nombre){
        try {
            PreparedStatement stmt= conn.prepareStatement("insert into Cliente(DNI,NOMBRE) values(?,?)");
            stmt.setString(1, dni);
            stmt.setString(2, nombre);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("No se pudo insertar un campo en la tabla Cliente");
        }

    }

    void modificarNombre(String nombre_antiguo, String nombre_nuevo){
        try{
            Statement stmt= conn.createStatement();
            stmt.executeUpdate("UPDATE Cliente set NOMBRE='"+ nombre_nuevo+ "' where NOMBRE='"+nombre_antiguo+"'");

        }catch(Exception e){
            System.out.println("No se pudo cambiar el nombre a "+ nombre_nuevo);
        }

    }

    void eliminarCliente(String dni){
        try {
            Statement stmt= conn.createStatement();
            stmt.executeUpdate("Delete from Cliente where DNI='"+dni+"'");
            
        } catch (Exception e) {
           System.out.println("Error al eliminar el cliente");
        }
    }


  
}
