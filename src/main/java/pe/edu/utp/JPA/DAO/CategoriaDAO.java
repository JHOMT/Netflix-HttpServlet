package pe.edu.utp.JPA.DAO;

import pe.edu.utp.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO(Connection connection) {
        this.connection = connection;
    }
    public void create(Categoria categoria) {
        String sql = "INSERT INTO Categorias( nombre, descripcion) VALUES(?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(Categoria categoria) {
        String sql = "UPDATE Categorias SET nombre=?, descripcion=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, categoria.getNombre());
            ps.setString(2, categoria.getDescripcion());
            ps.setInt(3, categoria.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM Categorias WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Categoria> findAll(){
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categorias";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Categoria categoria = Categoria.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public List<Categoria> findByPeliculaId(int id) {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categorias WHERE id IN (SELECT categoria_id FROM Peliculas_Categorias WHERE pelicula_id = ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Categoria categoria = Categoria.builder()
                        .id(rs.getInt("id"))
                        .nombre(rs.getString("nombre"))
                        .descripcion(rs.getString("descripcion"))
                        .build();
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
