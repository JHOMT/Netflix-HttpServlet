package pe.edu.utp.metodos.OrdenamientoUsuarios;

import pe.edu.utp.model.Usuario;

import java.util.Arrays;

public class OrdenamientoCorreo {
    public static Usuario[] mergeSort(Usuario[] words) {
        if (words == null || words.length <= 1) {
            return words;
        }
        int medio = words.length / 2;
        Usuario[] izquierda = Arrays.copyOfRange(words, 0, medio);
        Usuario[] derecha = Arrays.copyOfRange(words, medio, words.length);
        mergeSort(izquierda);
        mergeSort(derecha);
        merge(words, izquierda, derecha);
        return words;
    }

    private static void merge(Usuario[] resultado, Usuario[] izquierda, Usuario[] derecha) {
        int i = 0, j = 0, k = 0;

        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i].getUsername().compareTo(derecha[j].getUsername()) <= 0) {
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
}
