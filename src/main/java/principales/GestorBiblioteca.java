package principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;



import conexiones.MySQLConnection;

public class GestorBiblioteca {
    private final boolean reemplazar;
    public GestorBiblioteca(boolean reemplazar){
        this.reemplazar=reemplazar;

    }
    void creacionBD(String DB){
        MySQLConnection mysqlConnection= new MySQLConnection();
        try (
            Connection connection=mysqlConnection.getConnection() ;
             Statement stmt= connection.createStatement()) {

                if (reemplazar) {
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
