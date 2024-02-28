package pe.edu.utp.JPA.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
