package pe.edu.utp.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

public class BuscarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UsuarioController usuarioController = new UsuarioController();

        try {
            String usuarioBuscado = req.getParameter("txtNombre");
            if (usuarioBuscado == null || usuarioBuscado.trim().isEmpty()) {
                showErrorPage(resp, "El parámetro 'nombre' es obligatorio y no puede ser nulo o vacío");
                return;
            }

            List<Usuario> usuariosEncontrados = usuarioController.findByAll(usuarioBuscado);
            String filename = "src\\main\\resources\\web\\usuarios.html";
            String html = TextUTP.read(filename);
            StringBuilder tableRows = new StringBuilder();
            int i = 1;
            for (Usuario usuario : usuariosEncontrados) {
                String isAdminHTML = usuario.isAdmin() ? "<span class='check'>&#10003;</span>" : "<span class='x'>&#10007;</span>";
                tableRows.append("<tr><th scope='row'>").append(i).append("</th><td>")
                        .append(usuario.getNombre()).append("</td><td>")
                        .append(usuario.getEmail()).append("</td><td>")
                        .append("*********")
                        .append("</td><td>")
                        .append(isAdminHTML)
                        .append("</td></tr>");
                i++;
            }
            html = html.replace("${items}", tableRows.toString())
                    .replace("${pagination}", "");
            resp.setCharacterEncoding("UTF-8");
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
