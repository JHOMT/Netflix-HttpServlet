package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class AcercaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();
        String html = TextUTP.read("src\\main\\resources\\web\\acerca.html");
        String reporteHtml = html.replace("${nombre_usuario}", usuario.getNombre())
                .replace("${imagen_usuario}", usuario.getImagen());
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(reporteHtml);
    }
}
