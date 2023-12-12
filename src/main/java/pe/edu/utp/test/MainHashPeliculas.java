package pe.edu.utp.test;

import pe.edu.utp.model.Pelicula;
import pe.edu.utp.structures.ArbolPeliculas;
import pe.edu.utp.utils.DataReader;

import java.util.List;

public class MainHashPeliculas {
    public static void main(String[] args) {
        ArbolPeliculas arbolPeliculas = new ArbolPeliculas();
        Pelicula[] peliculas = DataReader.CargarPeliculas();

        for (Pelicula pelicula : peliculas) {
            arbolPeliculas.insertarPelicula(pelicula);
        }

        // Realizar búsquedas
        List<Pelicula> peliculaEncontrada = arbolPeliculas.buscarPeliculas("Titanic");
        if (peliculaEncontrada != null) {
            System.out.println("Pelicula encontrada: ");
            for (Pelicula pelicula : peliculaEncontrada) {
                System.out.println(pelicula);
            }
        } else {
            System.out.println("Pelicula no encontrada");
        }
    }
}
