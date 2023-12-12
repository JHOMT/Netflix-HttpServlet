package pe.edu.utp.metodos;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OrdenamientoUsuarios.OrdenamientoUsariosNombre;
import pe.edu.utp.model.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;

public class AppOrdenamientoBusqueda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
        Usuario[] words = Cargador.cargarUsuarios();
        Usuario[] wordsOrdenados = OrdenamientoUsariosNombre.mergeSort(words);
        int opcion = 0;
        String palabra = null;
        do {
            try {
                palabra = JOptionPane.showInputDialog(null, "Ingrese el nombre que desea buscar");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            int indice = binarySearch(wordsOrdenados, palabra);
            if (indice >= 0) {
                Usuario palabraEncontrada = wordsOrdenados[indice];
                String palabraBuscada = String.format(palabraEncontrada.toString());
                System.out.println(palabraBuscada);
                JOptionPane.showMessageDialog(null, palabraBuscada, "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,"Error: el nombre no fue encontrada", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            String formatoOpciones = """
                        Desea continuar en su búsqueda?
                        1 -> Si
                        2 -> No
                        """;
            try{
                do {
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null, formatoOpciones));
                    if (opcion < 1 || opcion > 2) {
                        JOptionPane.showMessageDialog(null,"El número registrado no es correcto");
                    }
                }while (opcion < 1 || opcion > 2);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error");
            }
        } while (opcion == 1);
    } catch (Exception e) {
        System.out.println("Error al cargar el archivo: " + e.getMessage());
    } finally {
        scanner.close();
    }
}
    public static int binarySearch(Usuario[] arreglo, String palabra) {
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int comparacion = arreglo[medio].getNombre().compareTo(palabra);
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
