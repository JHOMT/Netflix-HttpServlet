package pe.edu.utp.test;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.model.Pelicula;

import java.io.IOException;

public class TestPeliculas {
    public static void main(String[] args) throws IOException {
        Pelicula[] peliculas = Cargador.cargarPeliculas();
        for (Pelicula pelicula : peliculas) {
            System.out.println(pelicula.getName() + " " + pelicula.getProductor());
        }
    }
}
