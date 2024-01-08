package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.utils.model.Usuario;

import java.io.IOException;

public class IniciarSesionServlet extends HttpServlet {
    @Getter
    @Setter
    private static Usuario usuario;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioController usuarioController = new UsuarioController();
        String username = req.getParameter("email");
        String password = req.getParameter("password");
        Usuario usuario = usuarioController.login(username,password);
        if (usuario != null ) {
            this.setUsuario(usuario);
            if (usuario.isAdmin()) {
                resp.sendRedirect("/dashboard");
            } else {
                resp.sendRedirect("/listar");
            }
        } else {
            resp.sendRedirect("/error.html");
        }
    }
}
