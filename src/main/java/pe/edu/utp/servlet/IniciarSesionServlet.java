package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.structures.TablaHashUsuarios;
import pe.edu.utp.utils.DataReader;

import java.io.IOException;

public class IniciarSesionServlet extends HttpServlet {
    @Getter
    @Setter
    private static Usuario usuario;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            TablaHashUsuarios tablaUsuarios = new TablaHashUsuarios();
            Usuario[] usuarios = DataReader.CargarUsuarios();
            for (Usuario usuario : usuarios) {
                tablaUsuarios.agregarUsuario(usuario.getUsername(),usuario);
            }
            Usuario usuarioEncontrado = tablaUsuarios.buscarUsuario(username);
            if (usuarioEncontrado != null && usuarioEncontrado.getPassword() != null && usuarioEncontrado.getPassword().equals(password)) {
                this.setUsuario(usuarioEncontrado);
                if (usuarioEncontrado.isAdmin()) {
                    resp.sendRedirect("/dashboard");
                } else {
                    resp.sendRedirect("/listar");
                }
            } else {
                resp.sendRedirect("/error.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/error.html");
        }
    }
}
