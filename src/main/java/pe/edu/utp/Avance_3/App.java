package pe.edu.utp.Avance_3;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.model.Pelicula;
import java.util.*;

import static pe.edu.utp.Avance_3.Methods.*;

public class App {
    public static void main(String[] args) {
        Pelicula[] peliculas = Cargador.cargarPeliculas();
        Map<String, Pelicula> mapaPeliculasProtagonistas = Methods.crearMapaPeliculasProtagonistas(peliculas);
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar Película");
            System.out.println("2. Mostrar Películas por Clave");
            System.out.println("3. Mostrar Todas las Películas");
            System.out.println("4. Eliminar Película por Clave");
            System.out.println("5. Buscar Película");
            System.out.println("6. Tamaño del Mapa");
            System.out.println("7. Eliminar Todas las Películas");
            System.out.println("8. Salir");
            System.out.print("Ingresa una opción: ");
            do {
                try{
                    opcion = scanner.nextInt();
                }catch (Exception e){
                    opcion = 0;
                    scanner.nextLine();
                    System.out.println("Opción no válida. Por favor, ingrese un número válido: ");
                }
                if (opcion < 1 || opcion > 8) {
                    System.out.print("Opción no válida. Por favor, ingrese un número válido: ");
                }
            } while (opcion < 1 || opcion > 8);
            switch (opcion) {
                case 1:
                    scanner.nextLine();
                    System.out.println("_________________________________________________________________________________");
                    System.out.print("Ingrese la clave: ");
                    String clave = scanner.nextLine();
                    Pelicula nuevaPelicula = newPelicula();
                    mapaPeliculasProtagonistas.put(clave, nuevaPelicula);
                    break;
                case 2:
                    scanner.nextLine();
                    System.out.println("_________________________________________________________________________________");
                    System.out.print("Ingrese el nombre de la película para mostrar: ");
                    String nombreMostrar = scanner.nextLine();
                    Pelicula peliculaMostrar = mapaPeliculasProtagonistas.get(nombreMostrar);
                    if (peliculaMostrar != null) {
                        System.out.println(form());
                        System.out.println(peliculaMostrar);
                    } else {
                        System.out.println("No se encontraron resultados");
                    }
                    break;
                case 3:
                    System.out.println("_________________________________________________________________________________");
                    values(mapaPeliculasProtagonistas);
                    break;
                case 4:
                    scanner.nextLine();
                    System.out.println("_________________________________________________________________________________");
                    System.out.print("Ingrese el nombre de la película para eliminar: ");
                    String nombreEliminar = scanner.nextLine();
                    remove(mapaPeliculasProtagonistas, nombreEliminar);
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("_________________________________________________________________________________");
                    System.out.print("Ingrese el nombre, año o actores de la película que desea buscar: ");
                    String nombreBuscar = scanner.nextLine();
                    List<Pelicula> resultadosBusqueda = buscarPelicula(peliculas, nombreBuscar);
                    if (!resultadosBusqueda.isEmpty()) {
                        System.out.println("resultados de busqueda: ");
                        resultadosBusqueda.forEach(System.out::println);
                    } else {
                        System.out.println("No se encontraron resultados");
                    }
                    break;
                case 6:
                    System.out.println("_________________________________________________________________________________");
                    System.out.println("El tamaño del mapa es: " + mapaPeliculasProtagonistas.size());break;
                case 7:
                    System.out.println("_________________________________________________________________________________");
                    mapaPeliculasProtagonistas.clear();break;
                case 8:
                    System.out.println("_________________________________________________________________________________");
                    System.out.println("Saliendo del programa...");break;
                default:
                    System.out.println("_________________________________________________________________________________");
                    System.out.println("Opción no válida. Por favor, ingrese un número válido.");
            }
        } while (opcion != 8);

        scanner.close();
    }
}
