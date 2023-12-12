package pe.edu.utp.Avance_3;

import pe.edu.utp.model.Pelicula;

import java.util.*;

public class Methods {
    static Scanner scanner = new Scanner(System.in);
    public static void remove(Map<String, Pelicula> mapa, String key) {
        if (mapa.containsKey(key)) {
            mapa.remove(key);
        } else {
            System.out.println("No se encontraron resultados");
        }
    }

    public static void values(Map<String, Pelicula> mapa) {
        if (!mapa.isEmpty()) {
            System.out.println(form());
            mapa.values().forEach(System.out::println);
        } else {
            System.out.println("No se encontraron resultados");
        }
    }

    public static List<Pelicula> buscarPelicula(Pelicula[] peliculas, String filtro) {
        return buscarPeliculasRecursivo(peliculas, filtro, 0, new ArrayList<>());
    }

    private static List<Pelicula> buscarPeliculasRecursivo(Pelicula[] peliculas, String filtro, int index, List<Pelicula> resultados) {
        if (index >= peliculas.length) {
            return resultados;
        }
        Pelicula pelicula = peliculas[index];
        if (pelicula.getName().toLowerCase().contains(filtro.toLowerCase())
                || String.valueOf(pelicula.getAño()).equals(filtro)
                || pelicula.getActor().toLowerCase().contains(filtro.toLowerCase())) {
            resultados.add(pelicula);
        }
        return buscarPeliculasRecursivo(peliculas, filtro, index + 1, resultados);
    }

    // Búsqueda de películas asociadas a su protagonista principal usando tablas hash
    public static Map<String, Pelicula> crearMapaPeliculasProtagonistas(Pelicula[] peliculas) {
        Map<String, Pelicula> mapaPeliculasProtagonistas = new HashMap<>();
        for (Pelicula pelicula : peliculas) {
            mapaPeliculasProtagonistas.put(pelicula.getActor(), pelicula);
        }
        return mapaPeliculasProtagonistas;
    }

    // Método auxiliar para mostrar el formato de la tabla
    public static String form() {
        return String.format("""
                |----------------------------------------------------------LISTA-------------------------------------------------------------------------------|
                |           Nombre             | Año  |       Actor     |      Autor     |     Productor     |          Descripccion         |      Imagen     |
                |----------------------------------------------------------------------------------------------------------------------------------------------|
                """);
    }

    public static Pelicula newPelicula() {
        System.out.println("Ingrese el nombre de la pelicula");
        String nombre = scanner.nextLine();
        int año = 0;
        do {
            try {
                System.out.println("Ingrese el año de la pelicula");
                año = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error al registrar el año ");
                scanner.nextLine();
                año = -1;
            }
        } while (año < 0);
        System.out.println("Ingese el actor de la pelicula: ");
        String actor = scanner.nextLine();
        System.out.println("Ingese el autor de la pelicula: ");
        String autor = scanner.nextLine();
        System.out.println("Ingese el productor de la pelicula: ");
        String productor = scanner.nextLine();
        System.out.println("Ingese Una descripccion de la pelicula: ");
        String descripcion = scanner.nextLine();
        System.out.println("Ingese el imagen de la pelicula: ");
        String imagen = scanner.nextLine();
        System.out.println("Ingese el video de la pelicula: ");
        String video = scanner.nextLine();
        return new Pelicula(nombre, año, actor, autor, productor, descripcion, imagen, video);
    }

}
