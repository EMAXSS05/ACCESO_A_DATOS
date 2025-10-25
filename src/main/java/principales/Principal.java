package principales;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Principal {

    public static String BD = "biblioEmanuelR";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido, desea remplazar la Base de datos?, responder true o false");
        String reemplazar = sc.nextLine().toLowerCase();
        if (reemplazar.equals("true")) {
            GestorBiblioteca gestorBiblioteca = new GestorBiblioteca(true);
            gestorBiblioteca.creacionBD("biblioEmanuelR");
        }
        if (reemplazar.equals("false")) {
            GestorBiblioteca gestorBiblioteca = new GestorBiblioteca(false);
            gestorBiblioteca.creacionBD("biblioEmanuelR");
        }

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + BD, "usuario",
                    "usuario123");
            GestorLibros gestorLibros = new GestorLibros(connection);

            // añadir libro
            
              gestorLibros.addLibro("El quijote", "Miguel Cervantes", 1970,
              "3444-45453-333");
              gestorLibros.addLibro("La ciudad y los perros", "Mario Vargas Llosa", 2002,
              "2332-32323-232");
              gestorLibros.addLibro("El caballero Carmelo", "Abraham Valdelomar", 1990,
              "2132-32343-236");
              gestorLibros.addLibro("Paco Yunque", "Abraham Valdelomar", 2005,
             "2332-42353-292");
             
            gestorLibros.addLibro("Cien años de soledad", "Gabriel Garcia Marquez", 1967, "3434-34356-433");

            // obtener Libros
            gestorLibros.obtenerLibros();
            gestorLibros.obtenerLibros_Autor("Abraham Valdelomar");

            // comprobamos si se obtuvieron los ibros posteriores al año especificado
            gestorLibros.obtenerLibros_PorAnios(2000);


            gestorLibros.modificarTitulo("El caballero Carmelo", "el gallo colorado");
            //comprobamos si se modifico el titulo
            System.out.println("----Libros obtenidos despues de modificar un titulo------");
            gestorLibros.obtenerLibros();
            
            gestorLibros.obtenerLibros_Autor("Abraham Valdelomar");
            gestorLibros.modificarAutor("Miguel Cervantes", "yo");
            // comprobamos si se cambio el autor
            gestorLibros.obtenerLibros();

            gestorLibros.eliminarLibro("2332-32323-232");
            // comprobamos si se eliminó el libro
            System.out.println("------Tabla Libros despues de eliminar un libro-----");
            gestorLibros.obtenerLibros();

            gestorLibros.eliminarLibroSegun_anio(1990);
            // coprobamos si se eliminaron los libros anteriores al año especificado
            System.out.println("-------Tabla libros despues de borrar los libros anteriores a un año------");
            gestorLibros.obtenerLibros();

            gestorLibros.cleanLibros();
            // comprobamos si se borraron todos los libros
            System.out.println("--------Tabla libros despues de borrar todos los registros-----");
            gestorLibros.obtenerLibros();

        } catch (Exception e) {
            System.err.println("Error en una consulta");
        }
    }
}
