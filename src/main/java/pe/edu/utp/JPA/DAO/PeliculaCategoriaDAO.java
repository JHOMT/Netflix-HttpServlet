package pe.edu.utp.JPA.DAO;

import pe.edu.utp.model.Pelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaCategoriaDAO {
    private Connection connection;

    public PeliculaCategoriaDAO(Connection connection) {
        this.connection = connection;
    }
    public void registrarPeliculaCategoria(int id_pelicula, int id_categoria) {
        String sql = "INSERT INTO peliculas_categorias(pelicula_id, categoria_id) VALUES(?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id_pelicula);
            ps.setInt(2, id_categoria);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pelicula> listarPeliculasPorCategoria(int idCategoria) {
        String sql = "SELECT p.* FROM peliculas p INNER JOIN peliculas_categorias pc ON p.id = pc.pelicula_id WHERE pc.categoria_id = ?";
        List<Pelicula> peliculas = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idCategoria);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    Pelicula pelicula = new Pelicula();
                    pelicula.setId(rs.getInt("id"));
                    pelicula.setTitulo(rs.getString("titulo"));
                    pelicula.setLanzamiento(rs.getInt("lanzamiento"));
                    pelicula.setActor(rs.getString("actor"));
                    pelicula.setAutor(rs.getString("autor"));
                    pelicula.setProductor(rs.getString("productor"));
                    pelicula.setDescription(rs.getString("descripcion"));
                    pelicula.setImagen(rs.getString("imagen"));
                    pelicula.setUrl(rs.getString("url"));
                    peliculas.add(pelicula);
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peliculas;
    }
}
