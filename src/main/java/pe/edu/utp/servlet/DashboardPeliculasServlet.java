package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.data.Cargador;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.utils.DataReader;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class DashboardPeliculasServlet extends HttpServlet {
    int PAGE_SIZE = 12;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pelicula[] data = DataReader.CargarPeliculas();
        int totalPeliculas = data.length;
        int offset = 0;
        String offsetParam = req.getParameter("offset");

        if (offsetParam != null){
            offset = Integer.parseInt(offsetParam);
        }

        int totalPages = (int) Math.ceil((double) totalPeliculas/ PAGE_SIZE);
        offset = Math.min(offset, totalPeliculas-1);
        offset = Math.max(offset, 0);

        String filename = "src\\main\\resources\\web\\dashboard.html";
        String html = TextUTP.read(filename);

        String filename_item = "src\\main\\resources\\templates\\listado_item.html";
        String html_item = TextUTP.read(filename_item);
        StringBuilder items_html = new StringBuilder();

        for (int i = offset; i < totalPeliculas && i < offset + PAGE_SIZE; i++) {
            String item = html_item
                    .replace("${nombre}", data[i].getName())
                    .replace("${imagen}", data[i].getImagen());

            items_html.append(item);
        }
        String paginationHtml = "<nav aria-label='Page navigation example'><ul class='pagination justify-content-end'>";

        for (int i = 0; i < totalPages; i++) {
            int pageOffset = i * PAGE_SIZE;

            if (pageOffset == offset) {
                paginationHtml += "<li class='page-item active'><span class='page-link'>" + (i + 1) + "</span></li>";
            } else {
                paginationHtml += "<li class='page-item'><a class='page-link' href='?offset=" + pageOffset + "'>" + (i + 1) + "</a></li>";
            }
        }

        paginationHtml += "</ul></nav>";
        String reporte_html = html
                .replace("${items}", items_html.toString())
                .replace("${pagination}", paginationHtml);
        resp.getWriter().write(reporte_html);
    }
}
