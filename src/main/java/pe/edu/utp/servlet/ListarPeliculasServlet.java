package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.utils.model.Categoria;
import pe.edu.utp.utils.model.Pelicula;
import pe.edu.utp.utils.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

public class ListarPeliculasServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();

        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.findAll();

        CategoriaController categoriaController = new CategoriaController();

        String filename = "src\\main\\resources\\web\\listarPeliculasUsuarios.html";
        String html = TextUTP.read(filename);

        StringBuilder htmlBuilder = new StringBuilder();
        for (Pelicula pelicula : peliculas) {
            htmlBuilder.append("<a href=\"/detalle?id=").append(pelicula.getId()).append("\" class=\"card\">");
            htmlBuilder.append("<div class=\"poster\"><img src=\"img/peliculas/").append(pelicula.getImagen()).append("\" alt=\"Location Unknown\"></div>");
            htmlBuilder.append("<div class=\"details\">");
            htmlBuilder.append("<h1>").append(pelicula.getTitulo()).append("</h1>");
            htmlBuilder.append("<h2>").append(pelicula.getFecha_lanzamiento()).append("</h2>");
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
            htmlBuilder.append("<p class=\"desc\">").append(pelicula.getDescription()).append("</p>");
            htmlBuilder.append("</div>");
            htmlBuilder.append("</a>");
        }


        String reporte_html = html
                .replace("${nombre_usuario}", usuario.getNombre())
                .replace("${imagen_usuario}", usuario.getImagen())
                .replace("${items}", htmlBuilder.toString());
        resp.getWriter().write(reporte_html);
    }
}
