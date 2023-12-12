package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.App;
import pe.edu.utp.data.Cargador;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.utils.DataReader;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ListarPeliculasServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = "src\\main\\resources\\templates\\listado.html";
        String html = TextUTP.read(filename);

        String filename_item = "src\\main\\resources\\templates\\listado_item.html";
        String html_item = TextUTP.read(filename_item);
        StringBuilder items_html = new StringBuilder();

        String filename_imagenes = "src\\main\\resources\\templates\\muestra_imagenes.html";
        String html_imagen = TextUTP.read(filename_imagenes);
        StringBuilder imagenes_html = new StringBuilder();

        Pelicula[] data = DataReader.CargarPeliculas();

        for (Pelicula pelicula : data) {
            String item = html_item
                    .replace("${nombre}", pelicula.getName())
                    .replace("${imagen}", pelicula.getImagen());

            items_html.append(item);
        }

        Random random = new Random();
        Set<Integer> indicesSet = new HashSet<>();
        Pelicula imagenAzar[] = new Pelicula[3];
        for (int i = 0; i < 3; i++) {
            int indice;
            do {
                indice = random.nextInt(data.length);
            } while (!indicesSet.add(indice));
            imagenAzar[i] = data[indice];
        }
        String imagen = html_imagen
                .replace("${imagen0}", imagenAzar[0].getImagen()).replace("${nombre0}",imagenAzar[0].getName())
                .replace("${imagen1}", imagenAzar[1].getImagen()).replace("${nombre1}",imagenAzar[1].getName())
                .replace("${imagen2}", imagenAzar[2].getImagen()).replace("${nombre2}",imagenAzar[2].getName());
        imagenes_html.append(imagen);

        String reporte_html = html.replace("${imagenes}", imagenes_html.toString()).replace("${items}", items_html.toString());
        resp.getWriter().write(reporte_html);
    }
}
