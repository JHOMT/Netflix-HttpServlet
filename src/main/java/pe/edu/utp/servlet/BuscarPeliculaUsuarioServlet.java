package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.structures.ListMoviesUsuarios;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

public class BuscarPeliculaUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();
        PeliculaController peliculaController = new PeliculaController();
        try {
            String peliculaBuscada = req.getParameter("txtNombre");
            List<Pelicula> peliculasEncontradas = peliculaController.findByAll(peliculaBuscada);
            if (peliculaBuscada == null || peliculaBuscada.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'nombre' es obligatorio y no puede ser nulo o vacío");
                return;
            }
            String filename = "src\\main\\resources\\web\\listarPeliculasUsuarios.html";
            String html = TextUTP.read(filename);

            StringBuilder htmlBuilder = new StringBuilder();

            if (peliculasEncontradas != null && !peliculasEncontradas.isEmpty()) {
                ListMoviesUsuarios listMovies = new ListMoviesUsuarios();
                htmlBuilder.append(ListMoviesUsuarios.listMovies(peliculasEncontradas));
            } else {
                    htmlBuilder.append("<h2> No se encontro ninguna pelicula con el relacionada con: ")
                            .append( peliculaBuscada.toUpperCase())
                            .append(" </h2>");
            }

            html = html
                    .replace("${nombre_usuario}", usuario.getNombre())
                    .replace("${imagen_usuario}", usuario.getImagen())
                    .replace("${items}", htmlBuilder);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().println(html);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
