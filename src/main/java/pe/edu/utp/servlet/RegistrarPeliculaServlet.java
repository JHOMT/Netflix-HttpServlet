package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.utils.TextUTP;

import java.io.File;
import java.io.IOException;

public class RegistrarPeliculaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "src\\main\\java\\pe\\edu\\utp\\data\\peliculas.txt";

        String nombre = req.getParameter("nombre");
        int año = Integer.valueOf(req.getParameter("year"));
        String autor = req.getParameter("autor");
        String actor = req.getParameter("actor");
        String productor = req.getParameter("productor");
        String descripcion = req.getParameter("descripcion");
        String imagen = req.getParameter("imagen");
        String link = req.getParameter("link");

        try {
            String formatoData = String.format("\n%s˙%s˙%s˙%s˙%s˙%s˙%s˙%s", nombre, año, autor, actor, productor, descripcion, imagen, link);
            TextUTP.append(formatoData, data);
            resp.sendRedirect("/dashboard");
        } catch (IllegalArgumentException e) {
            String filenameError = "src\\main\\resources\\web\\error.html";
            String htmlError = TextUTP.read(filenameError);
            resp.getWriter().println(htmlError.replace("${error}", e.getMessage()));
        }
    }
}