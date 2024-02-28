package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.ComentarioDAO;
import pe.edu.utp.model.Comentario;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ComentarioController {
    private ComentarioDAO comentarioDAO;

    public ComentarioController() {
        Connection connection = ConnectionFactory.getConnection();
        comentarioDAO = new ComentarioDAO(connection);
    }
    public void delete(int id) {
        comentarioDAO.delete(id);
    }
    public void update(int id, int usuario_id, int pelicula_id, String comentario, LocalDateTime fecha_comentario) {
        comentarioDAO.update(new Comentario(id, usuario_id, pelicula_id, comentario, fecha_comentario));
    }
    public List<Comentario> findByPeliculaId(int PeliculaId){
        return comentarioDAO.findByPeliculaId(PeliculaId);
    }
    public List<Comentario> findByUsuarioId(int UsuarioId){
        return comentarioDAO.findByUsuarioId(UsuarioId);
    }
    public Map<String, String> findCreatorAndMovieByComentarioId(int ComentarioId){
        return comentarioDAO.findCreatorAndMovieByComentarioId(ComentarioId);
    }

    public void create(String comentario, int usuario, int idPelicula) {
        comentarioDAO.create(comentario, usuario, idPelicula);
    }
}
