package pe.edu.utp.metodos.OdenamientoPeliculas;

import pe.edu.utp.model.Pelicula;

import java.util.Arrays;

public class OrdenamientoPeliculasProductor {
    public static Pelicula[] mergeSort(Pelicula[] words) {
        if (words == null || words.length <= 1) {
            return words;
        }
        int medio = words.length / 2;
        Pelicula[] izquierda = Arrays.copyOfRange(words, 0, medio);
        Pelicula[] derecha = Arrays.copyOfRange(words, medio, words.length);
        mergeSort(izquierda);
        mergeSort(derecha);
        merge(words, izquierda, derecha);
        return words;
    }

    private static void merge(Pelicula[] resultado, Pelicula[] izquierda, Pelicula[] derecha) {
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i].getProductor().compareTo(derecha[j].getProductor()) <= 0) {
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
    public static int busquedaBinary(Pelicula[] peliculas, String nombre) {
        int izquierda = 0;
        int derecha = peliculas.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Pelicula peliculaEnMedio = peliculas[medio];
            if (peliculaEnMedio.getProductor().equals(nombre)) {
                return medio;
            } else if (peliculaEnMedio.getProductor().compareTo(nombre) < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}
