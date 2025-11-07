package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Pedido {

    Connection conn;

    public Pedido(Connection conn) {
        this.conn = conn;
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Pedido(\n" +
                    "ID int not null,\n" +
                    "Fecha varchar(30) not null,\n" +
                    "DNI_Cliente char(9) not null,\n" +
                    "primary key(ID),\n" +
                    "constraint fk_Cliente_pedido foreign key(DNI_Cliente) references Cliente(DNI) ON UPDATE CASCADE ON DELETE CASCADE)"

            );

        } catch (Exception e) {

        }
    }

    /*
     * Funcion para insertar un pedido
     */

    void insertarPedido(int id, String fecha, String dni_cliente) {

        try {
            PreparedStatement stmt = conn.prepareStatement("insert into Pedido(ID,Fecha,DNI_Cliente) values(?,?,?)");
            System.out.println("funciona");
            stmt.setInt(1, id);
            stmt.setString(2, fecha);
            stmt.setString(3, dni_cliente);
            System.out.println("funciona2");
            stmt.executeUpdate();
            System.out.println("funciona3");
        } catch (Exception e) {
            System.out.println("No se pudo insertar el pedido");
        }
    }

    /*
     * Consultar los pedidos realizados por un cliente. Se debe mostrar:
     * ▪ La fecha en la que realizó el pedido.
     * ▪ Los productos que compró.
     * ▪ El precio total del pedido
     */

    void consultarPedido() {
        try {
            Statement stmt = conn.createStatement();
            
            ResultSet result= stmt.executeQuery("SELECT  Fecha, Producto.ID as idProducto,Producto.Nombre as nombreProducto, (Producto.Cantidad* Producto.Precio) as PrecioTotal \n" +
                                "from Pedido join Producto\n" +
                                "ON Pedido.ID=Producto.ID");
            while (result.next()) {
                System.out.println("----Pedido----");
                System.out.println("Fecha: " + result.getString("Fecha"));
                System.out.println("ID Producto: "+result.getInt("idProducto"));
                System.out.println("Nombre Producto: "+result.getString("nombreProducto"));
                System.out.println("Precio total: "+result.getInt("PrecioTotal"));

            }
        } catch (Exception e) {
            System.out.println("No se pudo consultar la informacion");
        }
    }

}
