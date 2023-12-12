package pe.edu.utp.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.structures.TablaHashUsuarios;
import pe.edu.utp.utils.DataReader;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarUsuarioServlet extends HttpServlet {

    private final TablaHashUsuarios tablaUsuarios;

    public BuscarUsuarioServlet() {
        this.tablaUsuarios = new TablaHashUsuarios();
        Usuario[] usuarios = DataReader.CargarUsuarios();
        for (Usuario usuario : usuarios) {
            tablaUsuarios.agregarUsuario(usuario.getNombre(), usuario);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String usuarioBuscado = req.getParameter("txtNombre");
            if (usuarioBuscado == null || usuarioBuscado.trim().isEmpty()) {
                showErrorPage(resp, "El parámetro 'nombre' es obligatorio y no puede ser nulo o vacío");
                return;
            }

            List<Usuario> usuariosEncontrados = tablaUsuarios.buscarNombreUsuario(usuarioBuscado);
            String filename = "src\\main\\resources\\web\\usuarios.html";
            String html = TextUTP.read(filename);
            StringBuilder tableRows = new StringBuilder();
            int i = 1;
            for (Usuario usuario : usuariosEncontrados) {
                tableRows.append("<tr><th scope='row'>").append(i).append("</th><td>")
                        .append(usuario.getNombre()).append("</td><td>")
                        .append(usuario.getUsername()).append("</td><td>")
                        .append("******")
                        .append("</td><td>")
                        .append(usuario.isAdmin())
                        .append("</td></tr>");
                i++;
            }
            html = html.replace("${items}", tableRows.toString())
                    .replace("${pagination}", "");

            resp.getWriter().println(html);
        } catch (Exception e) {
            e.printStackTrace();
            showErrorPage(resp, "Error interno del servidor");
        }
    }

    private void showErrorPage(HttpServletResponse resp, String errorMessage) throws IOException {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMessage);
    }
}
