package com.example;

import java.sql.Connection;
import java.sql.Statement;

public class Bolechas {

    boolean reemplazar;
      public Bolechas(boolean reemplazar){
         this.reemplazar=reemplazar;
          
      }


    void crearBd(String BD){
    try {
    MySQLConnection mySQLConnection= new MySQLConnection();
    Connection conn= mySQLConnection.getConnection();
    Statement stmt=conn.createStatement();
    if (reemplazar) {
        stmt.executeUpdate("Drop database "+BD);
        stmt.executeUpdate("CREATE DATABASE "+BD);

    }
    else{
        stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS"+ BD);
    }

        
    } catch (Exception e) {
        System.out.println("No se pudo crear la base de datos ");
    }
    

    }

    
    


}
