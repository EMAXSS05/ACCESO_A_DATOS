package principales;

import java.sql.Connection;
import java.sql.Statement;

import conexiones.MySQLConnection;

public class GestorBibilioteca {
    private final boolean crear;
    public GestorBibilioteca(boolean crear){
        this.crear=crear;

    }
    void createDataBase(String DB){
        MySQLConnection mySQLConnection= new MySQLConnection();
        try (Connection connection= mySQLConnection.getConnection();
             Statement stmt= connection.createStatement()) {

                if (crear) {
                    stmt.executeUpdate("DROP DATABASE IF EXISTS "+DB);
                    System.out.println("Base de datos borrada");

                    stmt.executeUpdate("CREATE DATABASE "+ DB );
                    System.out.println("Base de datos creada");
                }
                else{
                    stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS "+DB);
                    System.out.println("Base de datos creada");
                }
        
               

        } catch (Exception e) {
         System.out.println("No se pudo crear la base de datos "+DB);
        }
    }



}
