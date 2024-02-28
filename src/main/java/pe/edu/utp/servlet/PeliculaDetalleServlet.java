package pe.edu.utp.servlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.JPA.Controller.ComentarioController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.JPA.Controller.UsuarioController;
import pe.edu.utp.model.Comentario;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PeliculaDetalleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuario usuarioLogeado = IniciarSesionServlet.getUsuario();
        PeliculaController peliculaController = new PeliculaController();
        ComentarioController comentarioController = new ComentarioController();
        UsuarioController usuarioController = new UsuarioController();
        int idPelucula = Integer.parseInt(request.getParameter("id"));

        Pelicula pelicula = peliculaController.findById(idPelucula);
        List<Comentario> comentariosList = comentarioController.findByPeliculaId(idPelucula);
        StringBuilder comentarios = new StringBuilder();

        for (Comentario comentario : comentariosList) {
            Usuario usuario = usuarioController.findById(comentario.getUsuario_id());
            if (usuario != null) {
                comentarios.append("<div class=\"comentario\">")
                        .append("<div class=\"usuario-imagen\">")
                        .append("<img src=\"img/usuarios/").append(usuario.getImagen()).append("\" alt=\"\">")
                        .append("</div>")
                        .append("<div class=\"info-comentario\">")
                        .append("<h3>@ <strong>");
                if (usuario.equals(usuarioLogeado)){
                    comentarios.append("Tu ");
                }else {
                    comentarios.append(usuario.getNombre());
                }
                comentarios
                        .append(" ").append("</strong>").append(formatDate(comentario.getFecha_comentario()))
                        .append("</h3>")
                        .append("<p>").append(comentario.getComentario()).append("</p>")
                        .append("</div>")
                        .append("</div><!---- fin comentario---->");
            }
        }
        String filenameDetalle = "src/main/resources/web/detalle.html";
        String detalleHTML = TextUTP.read(filenameDetalle);

        detalleHTML = detalleHTML
                .replace("${idPelicula}", String.valueOf(pelicula.getId()))
                .replace("${titulo}",pelicula.getTitulo())
                .replace("${video}", pelicula.getUrl())
                .replace("${descripcion}", pelicula.getDescription())
                .replace("${actor}", pelicula.getActor())
                .replace("${autor}", pelicula.getAutor())
                .replace("${productor}", pelicula.getProductor())
                .replace("${lanzamiento}", String.valueOf(pelicula.getLanzamiento()))
                .replace("${comentarios}",comentarios)
        ;
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(detalleHTML);
    }
    public String formatDate(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return localDateTime.format(formatter);
    }
}
