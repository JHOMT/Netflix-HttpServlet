package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.ComentarioController;
import pe.edu.utp.model.Usuario;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;

public class ComentarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String comentario = req.getParameter("txtComentario");
        int idPelicula = Integer.parseInt(req.getParameter("idPelicula"));
        Usuario usuario = IniciarSesionServlet.getUsuario();
        if (usuario == null) {
            resp.sendRedirect("iniciar_Sesion.html");
            return;
        }
        ComentarioController comentarioController = new ComentarioController();
        if (comentario == null || comentario.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'comentario' es obligatorio y no puede ser nulo o vacío");
            return;
        }
        comentarioController.create(comentario, usuario.getId(), idPelicula);
        resp.setCharacterEncoding("UTF-8");
        resp.sendRedirect("/detalle?id="+idPelicula);
        super.doPost(req, resp);
    }
}
