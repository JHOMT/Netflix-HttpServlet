package pe.edu.utp.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Categoria;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.util.List;

public class BuscarPeliculaDasboardServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        CategoriaController categoriaController = new CategoriaController();
        Usuario usuario = IniciarSesionServlet.getUsuario();
        PeliculaController peliculaController = new PeliculaController();
        String peliculaBuscada = req.getParameter("txtNombre");
        List<Pelicula> peliculasEncontradas = peliculaController.findByAll(peliculaBuscada);

        try {
            if (peliculaBuscada == null || peliculaBuscada.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'nombre' es obligatorio y no puede ser nulo o vacío");
                return;
            }

            String filename  = "src\\main\\resources\\web\\dashboardBusqueda.html";
            String html = TextUTP.read(filename);

            StringBuilder htmlBuilder = new StringBuilder();

            if (peliculasEncontradas != null && !peliculasEncontradas.isEmpty()) {
                for (Pelicula pelicula : peliculasEncontradas) {
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
                    htmlBuilder.append("<p class=\"desc\">").append(pelicula.getDescription()).append("</p>");
                    htmlBuilder.append("</div>");
                    htmlBuilder.append("</a>");
                }
            } else {
                htmlBuilder.append("<h2> No se encontro ninguna pelicula con el relacionada con: ")
                        .append( peliculaBuscada.toUpperCase())
                        .append(" </h2>");
            }

            String reporte_html = html
                    .replace("${items}", htmlBuilder.toString());
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(reporte_html);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
