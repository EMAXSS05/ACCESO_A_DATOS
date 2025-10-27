package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public final class Principal {

    static String BD = "Bolechas";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido, seleccione una opción: ");
        System.out.println("1.Crear Base de datos Bolechas \n" +
                "2.Crear tablas\n" +
                "3.Insertar un cliente\n" +
                "4.Insertar producto\n" +
                "5.Consultar informacion de un cliente\n" +
                "6.Consultar pedidos");
        int entrada = sc.nextInt();
        while (entrada != 0) {
            switch (entrada) {
                case 1:
                    System.out.println("Desea remplazar la Base de datos?, responder true o false");
                    String reemplazar = sc.nextLine().toLowerCase();
                    if (reemplazar.equals("true")) {
                        Bolechas bolechas = new Bolechas(true);
                        bolechas.crearBd(BD);
                    }
                    if (reemplazar.equals("false")) {
                        Bolechas bolechas = new Bolechas(false);
                        bolechas.crearBd(BD);
                    }
                    break;


              case 2:
              System.out.println("Creando tablas...");
              try {
                Connection conn= DriverManager.getConnection("jdbc://mysql://localhost:3306/"+BD,"root","root");
                GestorClientes cliente= new GestorClientes(conn);
                GestorProductos producto= new GestorProductos(conn);
                Pedido pedido= new Pedido(conn);
                
              } catch (Exception e) {
                System.out.println("No se pudo crear las tablas");
              }

            }
        }

    }
}
