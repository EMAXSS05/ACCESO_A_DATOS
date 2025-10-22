package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection implements DBConnection {
      private String URL="";
      private static final String USERNAME="usuario";
      private static final String PASSWORD="usuario23";

   public MySQLConnection(){
     this.URL="jdbc:mysql//localhost:3306/";
   }

    @Override
    public Connection getConnection() {
        try  {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la base de datos "+ e.getMessage());
            return null;
        }
    }

}
