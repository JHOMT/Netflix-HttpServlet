package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.util.List;

public class ListarUsuariosServlet extends HttpServlet {
    int PAGE_SIZE = 10;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsuarioController usuarioController = new UsuarioController();
        List<Usuario> users = usuarioController.findAll();

        int totalUsers = users.size();
        int offset = 0;
        String offsetParam = req.getParameter("offset");

        if (offsetParam != null) {
            offset = Integer.parseInt(offsetParam);
        }

        int totalPages = (int) Math.ceil((double) totalUsers / PAGE_SIZE);

        offset = Math.min(offset, totalUsers - 1);
        offset = Math.max(offset, 0);

        String html = TextUTP.read("src\\main\\resources\\web\\usuarios.html");
        String itemsHtml = "";

        for (int i = offset; i < totalUsers && i < offset + PAGE_SIZE; i++) {
            Usuario user = users.get(i);
            int id = i + 1;
            String isAdminHTML = user.isAdmin() ? "<span class='check'>&#10003;</span>" : "<span class='x'>&#10007;</span>";
            itemsHtml += "<tr><th scope='row'>" + id + "</th><td>" + user.getNombre() + "</td><td>" + user.getEmail() + "</td><td> ********** </td><td>"+isAdminHTML+"</td></tr>";
        }


        html = html.replace("${items}", itemsHtml);

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
        html = html.replace("${pagination}", paginationHtml);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(html);
    }
}
