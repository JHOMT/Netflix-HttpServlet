package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.structures.ArbolPeliculas;
import pe.edu.utp.utils.DataReader;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

public class BuscarPeliculaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();

        try {
            String peliculaBuscada = req.getParameter("txtNombre");

            if (peliculaBuscada == null || peliculaBuscada.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'nombre' es obligatorio y no puede ser nulo o vacío");
                return;
            }

            ArbolPeliculas arbolPeliculas = new ArbolPeliculas();
            Pelicula[] peliculas = DataReader.CargarPeliculas();

            for (Pelicula pelicula : peliculas) {
                arbolPeliculas.insertarPelicula(pelicula);
            }

            String filename;
            String html;
            if (usuario != null && usuario.isAdmin()) {
                filename = "src/main/resources/web/dashboardBusqueda.html";
            } else {
                filename = "src/main/resources/web/busqueda.html";
            }
            String filenameItem = "src/main/resources/templates/busqueda_item.html";
            String htmlItem = TextUTP.read(filenameItem);

            html = TextUTP.read(filename);

            List<Pelicula> peliculaEncontrada = arbolPeliculas.buscarPeliculas(peliculaBuscada);

            StringBuilder items = new StringBuilder();

            if (peliculaEncontrada != null && !peliculaEncontrada.isEmpty()) {
                for (Pelicula pelicula : peliculaEncontrada) {
                    String item = htmlItem
                                          .replace("${nombre}", pelicula.getName())
                                          .replace("${imagen}", pelicula.getImagen());
                    items.append(item);
                }
            } else {
                String salida = "<h2> No se encontro ninguna pelicula con el relacionada con: " + peliculaBuscada.toUpperCase() + " </h2>";
                html = html.replace("${items}", salida).replace("${pagination}", "<br>");
                resp.getWriter().println(html);
                return;
            }

            html = html.replace("${items}", items);
            resp.getWriter().println(html);

        } catch (Exception e) {
            e.printStackTrace();
            showErrorPage(resp, "Error interno del servidor");
        }
    }

    private void showErrorPage(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
    }
}
