package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.utils.model.Usuario;

import java.io.IOException;
import java.time.LocalDate;

public class RegistrarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioController usuarioController = new UsuarioController();

        String nombre = req.getParameter("nombre");
        String fileName = req.getParameter("imagen");
        String email = req.getParameter("correo");
        String contrasena = req.getParameter("contrasena");
        String adminParam = req.getParameter("admin");

        boolean isAdmin = "on".equals(adminParam);

        // Crear una instancia de Usuario con los datos del formulario
        Usuario nuevoUsuario = new Usuario(nombre, fileName, email, contrasena, LocalDate.now(), isAdmin);

        // Registrar al nuevo usuario en la base de datos
        boolean registroExitoso = usuarioController.insert(nuevoUsuario);

        if (registroExitoso) {
            IniciarSesionServlet.setUsuario(nuevoUsuario);
            resp.sendRedirect("/listar");
        } else {
            resp.sendRedirect("/error.html");
        }
    }

}
