package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.UTPBinary;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/registrarPelicula")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class RegistrarUsuarioServlet extends HttpServlet {
    private static final String DESTINO_IMAGES = "src/main/resources/web/img/usuarios/";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioController usuarioController = new UsuarioController();

        String nombre = req.getParameter("nombre");
        String email = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");
        String adminParam = req.getParameter("admin");
        boolean isAdmin = "on".equals(adminParam);
        try {
            Part filePart = req.getPart("imagen");
            String foto = guardarImagen(filePart);
            Usuario nuevoUsuario = new Usuario(nombre, foto, email, contrasena, LocalDate.now(), isAdmin);
            boolean registroExitoso = usuarioController.insert(nuevoUsuario);
            if (registroExitoso) {
                IniciarSesionServlet.setUsuario(nuevoUsuario);
                resp.sendRedirect("/listar");
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
            UTPBinary.echobin(data, DESTINO_IMAGES + foto);
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
