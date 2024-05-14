package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Categoria;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

import static pe.edu.utp.structures.ListMoviesUsuarios.twentyWords;

public class DashboardPeliculasServlet extends HttpServlet {
    private static final int PAGE_SIZE = 4;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();
        if (usuario == null) {
            resp.sendRedirect("/iniciar_Sesion.html");
            return;
        }
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.findAll();

        int totalPeliculas = peliculas.size();
        int offset = 0;
        String offsetParam = req.getParameter("offset");

        if (offsetParam != null) {
            offset = Integer.parseInt(offsetParam);
        }

        int totalPages = (int) Math.ceil((double) totalPeliculas / PAGE_SIZE);
        offset = Math.min(offset, totalPeliculas - 1);
        offset = Math.max(offset, 0);

        String reporte_html = generarHTMLPeliculas(peliculas, offset, totalPages);

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(reporte_html);
    }

    public static String generarHTMLPeliculas(List<Pelicula> peliculas, int offset, int totalPages) throws IOException {
        StringBuilder htmlBuilder = new StringBuilder();

        for (int i = offset; i < peliculas.size() && i < offset + PAGE_SIZE; i++) {
            Pelicula pelicula = peliculas.get(i);
            CategoriaController categoriaController = new CategoriaController();
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

        String paginationHtml = getPaginationHtml(totalPages, offset);
        String filename = "src\\main\\resources\\web\\dashboard.html";
        String html = TextUTP.read(filename);
        String reporte_html = html.replace("${items}", htmlBuilder.toString()).replace("${pagination}", paginationHtml);
        return reporte_html;
    }

    private static String getPaginationHtml(int totalPages, int offset) {
        StringBuilder paginationHtml = new StringBuilder("<nav aria-label='Page navigation example'><ul class='pagination justify-content-end'>");

        for (int i = 0; i < totalPages; i++) {
            int pageOffset = i * PAGE_SIZE;
            if (pageOffset == offset) {
                paginationHtml.append("<li class='page-item active'><span class='page-link'>").append(i + 1).append("</span></li>");
            } else {
                paginationHtml.append("<li class='page-item'><a class='page-link' href='?offset=").append(pageOffset).append("'>").append(i + 1).append("</a></li>");
            }
        }
        paginationHtml.append("</ul></nav>");
        return paginationHtml.toString();
    }

    private static String twentyWords(String description) {
        String[] words = description.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < words.length && i < 18; i++) {
            stringBuilder.append(words[i]).append(" ");
        }
        if (words.length > 18) {
            stringBuilder.append("...");
        }
        return stringBuilder.toString().trim();
    }
}