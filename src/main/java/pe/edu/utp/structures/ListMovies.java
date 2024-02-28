package pe.edu.utp.structures;

import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.model.Categoria;
import pe.edu.utp.model.Pelicula;

import java.util.List;

public class ListMovies {
    public String listMovies(List<Pelicula> peliculas){
        CategoriaController categoriaController = new CategoriaController();
        StringBuilder htmlBuilder = new StringBuilder();

        // Estructura estática de estrellas para reutilizar
        String estrellas = "<div class=\"rating\">" +
                "<i class=\"fas fa-star\"></i>" +
                "<i class=\"fas fa-star\"></i>" +
                "<i class=\"fas fa-star\"></i>" +
                "<i class=\"fas fa-star\"></i>" +
                "<i class=\"far fa-star\"></i>" +
                "</div>";

        for (Pelicula pelicula : peliculas) {
            htmlBuilder.append("<a href=\"/detalle?id=").append(pelicula.getId()).append("\" class=\"card\">");
            htmlBuilder.append("<div class=\"poster\"><img src=\"img/peliculas/").append(pelicula.getImagen()).append("\" alt=\"Location Unknown\"></div>");
            htmlBuilder.append("<div class=\"details\">");
            htmlBuilder.append("<h1>").append(pelicula.getTitulo()).append("</h1>");
            htmlBuilder.append("<h2>").append(pelicula.getLanzamiento()).append("</h2>");
            htmlBuilder.append(estrellas); // Reutilización de la estructura de estrellas

            // Consulta de categorías para la película actual
            List<Categoria> categorias = categoriaController.findByPeliculaId(pelicula.getId());
            htmlBuilder.append("<div class=\"tags\">");
            for (Categoria categoria : categorias) {
                htmlBuilder.append("<span class=\"tag\">").append(categoria.getNombre()).append("</span>");
            }
            htmlBuilder.append("</div>");

            htmlBuilder.append("<p class=\"desc\">").append(twentyWords(pelicula.getDescription())).append("</p>");
            htmlBuilder.append("</div>");
            htmlBuilder.append("</a>");
        }
        return htmlBuilder.toString();
    }
    public String twentyWords(String descripcion) {
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
}
