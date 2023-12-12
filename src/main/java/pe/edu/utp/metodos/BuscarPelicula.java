package pe.edu.utp.metodos;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasNombre;
import pe.edu.utp.model.Pelicula;

import java.io.IOException;
import java.util.Scanner;

public class BuscarPelicula {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        Pelicula[] peliculas = Cargador.cargarPeliculas();
        Pelicula[] peliculasOrdenadas = OrdenamientoPeliculasNombre.mergeSort(peliculas);
        for (Pelicula peliculasOrdenada : peliculasOrdenadas) {
            System.out.println(peliculasOrdenada);
        }
        System.out.println("Ingrese el nombre de la pelicula que desea buscar");
        String pelicula = scanner.nextLine();
        int indice = binarySearch(peliculasOrdenadas, pelicula);
        if (indice != -1){
            System.out.println(peliculasOrdenadas[indice]);
        }else {
            System.out.println("La pelicula no fue encontrada");
        }
    }
    public static int binarySearch(Pelicula[] arreglo, String palabra) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int comparacion = arreglo[medio].getName().compareTo(palabra);
            if (comparacion == 0) {
                return medio;
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}
