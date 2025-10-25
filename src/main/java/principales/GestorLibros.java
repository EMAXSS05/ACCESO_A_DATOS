package principales;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorLibros {
    private final Connection conn;
    public GestorLibros(Connection conn){
        this.conn=conn;
        try  {
            Statement stmt= conn.createStatement();
            String sql="CREATE TABLE IF NOT EXISTS Libros ( "+
            "ISBN varchar(30) not null,"+
            "titulo varchar(30) not null,"+
            "anioPublicacion int not null,"+
            "autor varchar(30) not null,"+
            "primary key(ISBN))";
            stmt.executeUpdate(sql);
            
        } catch (Exception e) {
            System.out.println("No se pudo crear la tabla Libros");
        }

    }
    void addLibro(String titulo, String autor, int anioPublicacion, String ISBN) {
        System.out.println("Añadiendo un libro...");
        String insertString = "insert into Libros(titulo,autor,anioPublicacion,ISBN) values (?,?,?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(insertString);
            statement.setString(1, titulo);
            statement.setString(2, autor);
            statement.setInt(3, anioPublicacion);
            statement.setString(4, ISBN);
            statement.executeUpdate();
            System.out.println("se añadio el libro");

        } catch (SQLException e) {
            System.out.println("No se pudo insertar en la tabla Libros");
        }

    }

    void obtenerLibros() {

        try {
            String sql = "select * from Libros";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("----Libro---\n");
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año Publicacion: " + rs.getInt("anioPublicacion"));
                System.out.println("ISBN: " + rs.getString("ISBN") + "\n");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo obtener todos los libro");
        }
    }

    void obtenerLibros_Autor(String autor) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Libros where autor='" + autor + "';");
            System.out.println("-----Libros del autor " + autor + "-----\n");
            while (rs.next()) {
                System.out.println("----Libro---");
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año publicación:" + rs.getInt("anioPublicacion"));
                System.out.println("ISBN: " + rs.getString("ISBN") + "\n");

            }
        } catch (SQLException e) {
            System.out.println("No se pudo obtener los libros del autor " + autor);
        }
    }

    void obtenerLibros_PorAnios(int anio) {
        System.out.println("---Libros lanzados despues del año " + anio);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Libros where anioPublicacion > " + anio + ";");
            while (rs.next()) {
                System.out.println("---Libro----");
                System.out.println("Titulo: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Año publicacion: " + rs.getInt("anioPublicacion"));
                System.out.println("ISBN: " + rs.getString("ISBN") + "\n");
            }
        } catch (SQLException e) {
            System.out.println("No se pudo seleccionar los libros posteriores al año " + anio);
        }
    }

    void modificarTitulo(String tituloAntiguo, String tituloNuevo) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("update Libros set titulo='" + tituloNuevo + "' where titulo='" + tituloAntiguo + "'");
        } catch (SQLException e) {
            System.out.println("No se pudo modificar el titulo a " + tituloNuevo);
        }
    }

    void modificarAutor(String autorAntiguo, String autorNuevo) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("update Libros set autor='" + autorNuevo + "' where autor='"+ autorAntiguo + "';");
        } catch (Exception e) {
            System.out.println("No se pudo modificar el autor " + autorAntiguo + "a " + autorNuevo);
        }
    }

    void eliminarLibro(String ISBN){
       try {
        Statement stmt= conn.createStatement();
        stmt.executeUpdate("Delete from Libros where ISBN='"+ISBN+"';");
       } catch (Exception e) {
        System.out.println("No se pudo borrar el libro con ISBN "+ISBN);
       }
    }

    void eliminarLibroSegun_anio(int anio){
        try {
            Statement stmt= conn.createStatement();
            stmt.executeUpdate("Delete from Libros where anioPublicacion<' "+anio+"';");
        } catch (Exception e) {
            System.out.println("No se pudo borrar los libros anteriores al año "+anio);
        }
    }

    void cleanLibros(){
        try {
            Statement stmt= conn.createStatement();
            stmt.executeUpdate("Delete from Libros");
        } catch (Exception e) {
            System.out.println("No se pudo borrar todos los registros de la tabla Libros");
        }
    }



}
