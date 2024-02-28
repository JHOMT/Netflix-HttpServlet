package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pe.edu.utp.JPA.Controller.PeliculaCategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.utils.UTPBinary;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/registrar")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class RegistrarPeliculaServlet extends HttpServlet {
    private static final String DESTINE_IMAGINES = "src/main/resources/web/img/peliculas/";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PeliculaController peliculaController = new PeliculaController();
        PeliculaCategoriaController peliculaCategoriaController = new PeliculaCategoriaController();
        String nombre = req.getParameter("nombre");
        int año = Integer.valueOf(req.getParameter("year"));
        String autor = req.getParameter("autor");
        String actor = req.getParameter("actor");
        String productor = req.getParameter("productor");
        String descripcion = req.getParameter("descripcion");
        String link = req.getParameter("link");
        // Categorias
        Map<String, Integer> categorias = new HashMap<>();
        categorias.put("1", 1);
        categorias.put("2", 2);
        categorias.put("3", 3);
        categorias.put("4", 4);
        categorias.put("5", 5);
        categorias.put("6", 6);
        categorias.put("7", 7);
        categorias.put("8", 8);
        categorias.put("9", 9);
        try {
            Part filePart = req.getPart("imagen");
            String foto = guardarImagen(filePart);

            Pelicula nuevaPelicula = new Pelicula(nombre, año, actor, autor, productor, descripcion, foto, link);

            boolean registroExitoso = peliculaController.create(nuevaPelicula);

            if (registroExitoso) {
                resp.sendRedirect("/dashboard");
                Pelicula pelicula = peliculaController.findByTitleAndImage(nuevaPelicula.getTitulo(), nuevaPelicula.getImagen());
                for (Map.Entry<String, Integer> entry : categorias.entrySet()) {
                    String key = entry.getKey();
                    Integer value = entry.getValue();
                    if (req.getParameter(key) != null) {
                        peliculaCategoriaController.registrarPeliculaCategoria(pelicula.getId(), value);
                    }
                }
            } else {
                resp.sendRedirect("/error.html");
            }

        } catch (IllegalArgumentException e) {
            String htmlError = "src/main/resources/templates/error.html";
            String error = pe.edu.utp.utils.TextUTP.read(htmlError);
            resp.getWriter().println(error.replace("${error}", e.getMessage()));
        }
    }

    private String guardarImagen(Part filePart) throws IOException {
        String foto = getFileName(filePart);
        if (foto != null && !foto.isEmpty()) {
            byte[] data = filePart.getInputStream().readAllBytes();
            UTPBinary.echobin(data, DESTINE_IMAGINES + foto);
            return foto;
        } else {
            throw new IllegalArgumentException("Debe adjuntar una imagen.");
        }
    }

    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}