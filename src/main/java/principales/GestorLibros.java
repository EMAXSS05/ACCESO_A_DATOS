package principales;

import java.sql.Connection;
import java.sql.Statement;

public class GestorLibros {
    private final Connection conn;
    public GestorLibros(Connection conn){
        this.conn=conn;
        try  {
            Statement stmt= conn.createStatement();
            String sql="CREATE TABLE IF NOT EXISTS LIBROS ( "+
            "ISBN varchar(30) not null,"+
            "Titulo varchar(30) not null,"+
            "AnioPubli int not null,"+
            "Autor varchar(30) not null,"+
            "primary key(ISBN))";
            stmt.executeUpdate(sql);
            
        } catch (Exception e) {
            System.out.println("No se pudo crear la tabla Libros");
        }
    }
}
