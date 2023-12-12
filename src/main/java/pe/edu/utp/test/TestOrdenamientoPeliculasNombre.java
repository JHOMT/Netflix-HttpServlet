package pe.edu.utp.test;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasNombre;
import pe.edu.utp.model.Pelicula;

import java.io.IOException;

public class TestOrdenamientoPeliculasNombre {
    public static void main(String[] args) throws IOException {
        Pelicula[] peliculas = Cargador.cargarPeliculas();
        Pelicula[] peliculasOrdenadosNombre = OrdenamientoPeliculasNombre.mergeSort(peliculas);
        for (Pelicula pelicula : peliculasOrdenadosNombre) {
            System.out.println(pelicula.getName());
        }
    }
}
