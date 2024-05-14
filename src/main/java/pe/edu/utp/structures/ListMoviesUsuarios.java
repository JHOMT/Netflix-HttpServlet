package pe.edu.utp.structures;

import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Categoria;
import pe.edu.utp.model.Pelicula;

import java.util.List;

public class ListMoviesUsuarios {
    public static String listMovies(List<Pelicula> peliculas){
        CategoriaController categoriaController = new CategoriaController();
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<div class=\"products\">");
        for (Pelicula pelicula : peliculas) {
            htmlBuilder.append("<a href=\"/detalle?id=").append(pelicula.getId()).append("\" class=\"card\">");
            htmlBuilder.append("<div class=\"poster\"><img src=\"img/peliculas/").append(pelicula.getImagen()).append("\" alt=\"Location Unknown\"></div>");
            htmlBuilder.append("<div class=\"details\">");
            htmlBuilder.append("<h1>").append(pelicula.getTitulo()).append("</h1>");
            htmlBuilder.append("<h2>").append(pelicula.getLanzamiento()).append("</h2>");
            htmlBuilder.append("<div class=\"rating\">");
            htmlBuilder.append("<i class=\"fas fa-star\"></i>");
            htmlBuilder.append("<i class=\"fas fa-star\"></i>");
            htmlBuilder.append("<i class=\"fas fa-star\"></i>");
            htmlBuilder.append("<i class=\"fas fa-star\"></i>");
            htmlBuilder.append("<i class=\"far fa-star\"></i>");
            htmlBuilder.append("</div>");
            htmlBuilder.append("<div class=\"tags\">");

            List<Categoria> categorias = categoriaController.findByPeliculaId(pelicula.getId());
            for (Categoria categoria : categorias) {
                htmlBuilder.append("<span class=\"tag\">").append(categoria.getNombre()).append("</span>");
            }

            htmlBuilder.append("</div>");
            htmlBuilder.append("<p class=\"desc\">").append(twentyWords(pelicula.getDescription())).append("</p>");
            htmlBuilder.append("</div>");
            htmlBuilder.append("</a>");
        }
        htmlBuilder.append("</div>");
        return htmlBuilder.toString();
    }

    public static String twentyWords(String descripcion) {
        String[] words = descripcion.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < words.length && i < 15; i++) {
            stringBuilder.append(words[i]).append(" ");
        }
        if (words.length > 15) {
            stringBuilder.append("...");
        }
        return stringBuilder.toString().trim();
    }

    public static void main(String[] args) {
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.findAll();
        System.out.println(listMovies(peliculas));
    }
}
