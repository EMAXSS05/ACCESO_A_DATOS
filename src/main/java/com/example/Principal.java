package com.example;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public final class Principal {

    static String BD = "Bolechas";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido, seleccione una opción: ");
       

        int entrada = 7;
        while (entrada != 0) {
             System.out.println("1.Crear Base de datos Bolechas \n" +
                "2.Crear tablas\n" +
                "3.Insertar un cliente\n" +
                "4.Cambiar nombre del cliente\n"+
                "5.Eliminar un cliente\n"+
                "6.Insertar un producto\n" +
                "7.Cambiar\n"+
                "7.Consultar informacion de un cliente\n" +
                "8.Consultar pedidos");
            entrada=sc.nextInt();
            sc.nextLine();
            switch (entrada) {
                case 1:
                    System.out.println("Desea remplazar la Base de datos?, responder true o false");
                    String reemplazar = sc.nextLine().toLowerCase();
                    if (reemplazar.equals("true")) {
                        Bolechas bolechas = new Bolechas(true);
                        bolechas.crearBd(BD);
                        System.out.println("La base de datos " + BD+ " ha sido creado exitosamente");
                    }
                    if (reemplazar.equals("false")) {
                        Bolechas bolechas = new Bolechas(false);
                        bolechas.crearBd(BD);
                    }
                    break;


              case 2:
              System.out.println("Creando tablas...");
              try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                GestorClientes cliente= new GestorClientes(conn);
                System.out.println("Se creó la tabla Cliente");
                Pedido pedido= new Pedido(conn);
                System.out.println("Se creó la tabla Pedido");
                GestorProductos producto= new GestorProductos(conn);
                System.out.println("Se creó la tabla Producto");

               
                
              } catch (Exception e) {
                System.out.println("No se pudo crear las tablas");
              }

              break;

              case 3:
              try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                System.out.println("Inserte un nro de dni: ");
                String dni= sc.nextLine();
                System.out.println("Inserte el nombre del cliente: ");
                String nombre= sc.nextLine();
                GestorClientes cliente= new GestorClientes(conn);
                cliente.insertarCliente(dni, nombre);
                System.out.println("Se insertó el cliente");
              } catch (Exception e) {
                System.out.println("No se pudo insertar el cliente");
              }
              break;

              case 4:
                try {
                     Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                     System.out.println("Introduzca el nombre a remplazar: ");
                     String nombre_antiguo=sc.nextLine();
                     System.out.println("Introduzca un nuevo nombre para el cliente");
                     String nombre_nuevo= sc.nextLine();
                     GestorClientes cliente= new GestorClientes(conn);
                     cliente.modificarNombre(nombre_antiguo, nombre_nuevo);
                     System.out.println("Se cambió el nombre a "+nombre_nuevo);
                } catch (Exception e) {
                    System.out.println("No se pudo cambiar el nombre del cliente");
                }
              break;
              case 5:

              try {
                 Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                 System.out.println("Introduzca el dni del cliente a eliminar: ");
                 String dni= sc.nextLine();
                 GestorClientes clientes= new GestorClientes(conn);
                clientes.eliminarCliente(dni);
                System.out.println("Se eliminó el cliente con el dni "+dni);
              } catch (Exception e) {
                System.out.println("No se pudo borrar el cliente" );
              }
              break;


              case 6:
              try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                GestorProductos productos= new GestorProductos(conn);
                productos
              } catch (Exception e) {
                // TODO: handle exception
              }

              break;

              case 7:
              try {
                Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+BD,"root","root");
                Pedido pedido= new Pedido(conn);
                String fechaString = "24-04-2023";
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

                Date fecha = (Date) formato.parse(fechaString);
            
                
                pedido.insertarPedido(1, fecha,"42915735");
                
              } catch (Exception e) {
                System.out.println("No se pudo insertar el pedido");
              }
              break;



            }
        }

    }
}
