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

public class ListarPeliculasServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();
        if (usuario == null) {
            resp.sendRedirect("/iniciar_Sesion.html");
            return;
        }
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.findAll();

        String filename = "src\\main\\resources\\web\\listarPeliculasUsuarios.html";
        String html = TextUTP.read(filename);

        String htmlBuilder = ListMoviesUsuarios.listMovies(peliculas);
        String reporte_html = html
                .replace("${nombre_usuario}", usuario.getNombre())
                .replace("${imagen_usuario}", usuario.getImagen())
                .replace("${items}", htmlBuilder);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(reporte_html);
    }

}
