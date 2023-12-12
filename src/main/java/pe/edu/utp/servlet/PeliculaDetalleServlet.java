package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OdenamientoPeliculas.OrdenamientoPeliculasNombre;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.utils.DataReader;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class PeliculaDetalleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombrePelicula = request.getParameter("nombre");

        Pelicula[] peliculas = DataReader.CargarPeliculas();
        Pelicula[] peliculasOrdenadas = OrdenamientoPeliculasNombre.mergeSort(peliculas);
        int indice = busquedaBinary(peliculasOrdenadas, nombrePelicula);

        if (indice != -1) {
            Pelicula pelicula = peliculasOrdenadas[indice];
            StringBuilder salida = new StringBuilder();
            salida.append("<div class=\"descripcion\">")
                    .append("<div class=\"titulo\">")
                    .append("<h1>").append(pelicula.getName()).append("</h1>")
                    .append("</div>")
                    .append("<div class=\"caracteristicas\">")
                    .append("<div class=\"video-container\">").append(pelicula.getVideoLink()).append("</div>")
                    .append("<div class=\"descripcion\">")
                    .append("<h4>Descripcion:").append("</h4>")
                    .append("<p>").append(pelicula.getDescription()).append("</p>")
                    .append("<h4>Actor: ").append("</h4>")
                    .append("<p>").append(pelicula.getActor()).append("</p>")
                    .append("<h4>Autor: ").append("</h4>")
                    .append("<p>").append(pelicula.getAutor()).append("</p>")
                    .append("<h4>Productor: ").append("</h4>")
                    .append("<p>").append(pelicula.getProductor()).append("</p>")
                    .append("<h4>Año:").append("</h4>")
                    .append("<p>").append(pelicula.getAño()).append("</p>")
                    .append("</div>")
                    .append("</div>");
            String filenameDetalle = "src/main/resources/web/detalle.html";
            String detalleHTML = TextUTP.read(filenameDetalle);
            detalleHTML = detalleHTML.replace("${nombre}",pelicula.getName()).replace("${informacion}", salida.toString());

            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(detalleHTML);
        } else {
            String errorHMTL = "src\\main\\resources\\web\\error.html";
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(errorHMTL);
        }
    }
    public static int busquedaBinary(Pelicula[] peliculas, String nombre) {
        int izquierda = 0;
        int derecha = peliculas.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            Pelicula usuarioEnMedio = peliculas[medio];
            int comparacion = usuarioEnMedio.getName().compareTo(nombre);
            if (comparacion == 0 ) {
                return medio;
            } else if (comparacion < 0) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}
