package pe.edu.utp.metodos;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasActor;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasAutor;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasNombre;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasProductor;
import pe.edu.utp.model.Pelicula;

import javax.swing.*;
import java.util.Scanner;

public class AppOrdenamientoPeliculas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Pelicula[] peliculas = Cargador.cargarPeliculas();
            Pelicula[] peliculasOrdenadas = new Pelicula[0];
            String salida= null;
            int opcion = 0;
            String palabra = null;
            int opcionFormato = 0;
            String formatoOpciones = """
                    Seleccione su ordenamiento de peliculas
                    1 -> Por nombre
                    2 -> Por Actor
                    3 -> Por Autor
                    4 -> Por Productor
                    """;
            do {
                try {

                    do {
                        opcionFormato = Integer.parseInt(JOptionPane.showInputDialog(null, formatoOpciones));
                        if (opcionFormato < 1 || opcionFormato > 4) {
                            JOptionPane.showMessageDialog(null,"Error: El dato debe ser numerico", "Advertencia", JOptionPane.WARNING_MESSAGE);
                        }
                    }while (opcionFormato < 1 || opcionFormato > 4);
                }catch (Exception e){
                    JOptionPane.showMessageDialog(null,"Error", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
                if (opcionFormato == 1){
                    System.out.println("\nOrdenamiendo por nombre");
                    peliculasOrdenadas = OrdenamientoPeliculasNombre.mergeSort(peliculas);
                } else if (opcionFormato == 2){
                    System.out.println("\nOrdenamiendo por actor");
                    peliculasOrdenadas = OrdenamientoPeliculasActor.mergeSort(peliculas);
                }else if (opcionFormato == 3){
                    System.out.println("\nOrdenamiendo por autor");
                    peliculasOrdenadas = OrdenamientoPeliculasAutor.mergeSort(peliculas);
                }else if (opcionFormato == 4){
                    System.out.println("\nOrdenamiendo por productor");
                    peliculasOrdenadas = OrdenamientoPeliculasProductor.mergeSort(peliculas);
                }
                for (Pelicula peliculasOrdenada : peliculasOrdenadas) {
                    System.out.println(peliculasOrdenada);
                }
                String formatoOpcionesSalida = """
                        Desea continuar en su búsqueda?
                        1 -> Si
                        2 -> No
                        """;
                try{
                    do {
                        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, formatoOpcionesSalida));
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
}
