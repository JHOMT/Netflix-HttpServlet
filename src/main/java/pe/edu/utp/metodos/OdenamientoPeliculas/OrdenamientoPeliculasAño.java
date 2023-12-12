package pe.edu.utp.metodos.OdenamientoPeliculas;

import pe.edu.utp.model.Pelicula;
import java.util.Arrays;

public class OrdenamientoPeliculasAño {
    public static Pelicula[] mergeSort(Pelicula[] peliculas) {
        if (peliculas == null || peliculas.length <= 1) {
            return peliculas;
        }
        int medio = peliculas.length / 2;
        Pelicula[] izquierda = Arrays.copyOfRange(peliculas, 0, medio);
        Pelicula[] derecha = Arrays.copyOfRange(peliculas, medio, peliculas.length);
        mergeSort(izquierda);
        mergeSort(derecha);
        merge(peliculas, izquierda, derecha);
        return peliculas;
    }

    private static void merge(Pelicula[] resultado, Pelicula[] izquierda, Pelicula[] derecha) {
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i].getAño() <= derecha[j].getAño()) {
                resultado[k++] = izquierda[i++];
            } else {
                resultado[k++] = derecha[j++];
            }
        }
        while (i < izquierda.length) {
            resultado[k++] = izquierda[i++];
        }
        while (j < derecha.length) {
            resultado[k++] = derecha[j++];
        }
    }

    public static int busquedaBinary(Pelicula[] peliculas, int año) {
        int izquierda = 0;
        int derecha = peliculas.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Pelicula peliculaEnMedio = peliculas[medio];
            if (peliculaEnMedio.getAño() == año) {
                return medio;
            } else if (peliculaEnMedio.getAño() < año) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}
