package pe.edu.utp.JPA.DAO;

import pe.edu.utp.model.Comentario;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComentarioDAO {
    private Connection connection;

    public ComentarioDAO(Connection connection) {
        this.connection = connection;
    }
    public void create(String comentario, int idUsuario, int idPelicula) {
        String sql = "INSERT INTO Comentarios(usuario_id, pelicula_id, comentario, fecha_comentario) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idPelicula);
            ps.setString(3, comentario);
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Comentario comentario) {
        String sql = "UPDATE Comentarios SET usuario_id=?, pelicula_id=?, comentario=?, fecha_comentario=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, comentario.getUsuario_id());
            ps.setInt(2, comentario.getPelicula_id());
            ps.setString(3, comentario.getComentario());
            ps.setTimestamp(4, Timestamp.valueOf(comentario.getFecha_comentario()));
            ps.setInt(5, comentario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM Comentarios WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Comentario> findByPeliculaId(int peliculaId) {
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM Comentarios WHERE pelicula_id=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, peliculaId); // Establecer el valor del parámetro pelicula_id
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Comentario comentario = Comentario.builder()
                        .id(rs.getInt("id"))
                        .usuario_id(rs.getInt("usuario_id"))
                        .pelicula_id(rs.getInt("pelicula_id"))
                        .comentario(rs.getString("comentario"))
                        .fecha_comentario(rs.getTimestamp("fecha_comentario").toLocalDateTime())
                        .build();
                comentarios.add(comentario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return comentarios;
    }

    public List<Comentario> findByUsuarioId(int UsuarioId){
        List<Comentario> comentarios = new ArrayList<>();
        String sql = "SELECT * FROM Comentarios WHERE usuario_id=?";
        try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Comentario comentario = Comentario.builder()
                        .id(rs.getInt("id"))
                        .usuario_id(rs.getInt("usuario_id"))
                        .pelicula_id(rs.getInt("pelicula_id"))
                        .comentario(rs.getString("comentario"))
                        .fecha_comentario(rs.getTimestamp("fecha_comentario").toLocalDateTime())
                        .build();
                comentarios.add(comentario);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return comentarios;
    }
    public Map<String, String> findCreatorAndMovieByComentarioId(int comentarioId) {
        Map<String, String> comentarioInfo = new HashMap<>();
        String sql = "SELECT c.id AS comentario_id, u.nombre AS nombre_usuario, p.titulo AS titulo_pelicula " +
                "FROM Comentarios c " +
                "JOIN Usuarios u ON c.usuario_id = u.id " +
                "JOIN Peliculas p ON c.pelicula_id = p.id " +
                "WHERE c.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, comentarioId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                comentarioInfo.put("comentario_id", String.valueOf(rs.getInt("comentario_id")));
                comentarioInfo.put("nombre_usuario", rs.getString("nombre_usuario"));
                comentarioInfo.put("titulo_pelicula", rs.getString("titulo_pelicula"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comentarioInfo;
    }
}
