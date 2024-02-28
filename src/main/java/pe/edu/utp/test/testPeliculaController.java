package pe.edu.utp.test;

import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Pelicula;

import java.util.List;

public class testPeliculaController {
    public static void main(String[] args) {
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas =peliculaController.findByAll("2019");
        peliculas.forEach(System.out::println);
    }
}
