package pe.edu.utp.JPA.DAO;

import pe.edu.utp.model.Pelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAO {
    private Connection connection;

    public PeliculaDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean create(Pelicula pelicula) {
        String sql = "INSERT INTO peliculas(titulo, lanzamiento, actor, autor, productor, descripcion, imagen, url) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pelicula.getTitulo());
            ps.setInt(2, pelicula.getLanzamiento());
            ps.setString(3, pelicula.getActor());
            ps.setString(4, pelicula.getAutor());
            ps.setString(5, pelicula.getProductor());
            ps.setString(6, pelicula.getDescription());
            ps.setString(7, pelicula.getImagen());
            ps.setString(8, pelicula.getUrl());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean update(Pelicula pelicula) {
        String sql = "UPDATE peliculas SET titulo=?, lanzamiento=?, actor=?, autor=?, productor=?, descripcion=?, imagen=?, url=? WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pelicula.getTitulo());
            ps.setInt(2, pelicula.getLanzamiento());
            ps.setString(3, pelicula.getActor());
            ps.setString(4, pelicula.getAutor());
            ps.setString(5, pelicula.getProductor());
            ps.setString(6, pelicula.getDescription());
            ps.setString(7, pelicula.getImagen());
            ps.setString(8, pelicula.getUrl());
            ps.setInt(9, pelicula.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean delete(int id) {
        String sql = "DELETE FROM peliculas WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Pelicula finByTitle(String titulo) {
        String sql = "SELECT * FROM peliculas WHERE titulo=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.executeQuery();
            return new Pelicula();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Pelicula> findByAll(String filter){
        List<Pelicula> peliculasEncontradas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas WHERE titulo LIKE ? OR lanzamiento LIKE ? OR actor LIKE ? OR autor LIKE ? or productor LIKE ? OR descripcion LIKE ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            String likeFilter = "%" + filter + "%";
            for (int i = 1; i <= 6; i++) {
                ps.setString(i, likeFilter);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Aquí debes crear objetos Pelicula a partir de los resultados de la consulta y agregarlos a la lista
                Pelicula pelicula = new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("lanzamiento"),
                        rs.getString("actor"),
                        rs.getString("autor"),
                        rs.getString("productor"),
                        rs.getString("descripcion"),
                        rs.getString("imagen"),
                        rs.getString("url")
                );
                peliculasEncontradas.add(pelicula);
            }

            return peliculasEncontradas;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Pelicula> findAll() {
        List<Pelicula> peliculasEncontradas = new ArrayList<>();
        String sql = "SELECT * FROM peliculas";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Aquí debes crear objetos Pelicula a partir de los resultados de la consulta y agregarlos a la lista
                Pelicula pelicula = new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("lanzamiento"),
                        rs.getString("actor"),
                        rs.getString("autor"),
                        rs.getString("productor"),
                        rs.getString("descripcion"),
                        rs.getString("imagen"),
                        rs.getString("url")
                );
                peliculasEncontradas.add(pelicula);
            }
            return peliculasEncontradas;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Pelicula findById(int peliculaId) {
        String sql = "SELECT * FROM peliculas WHERE id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, peliculaId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Aquí debes crear un objeto Pelicula a partir de los resultados de la consulta
                return new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("lanzamiento"),
                        rs.getString("actor"),
                        rs.getString("autor"),
                        rs.getString("productor"),
                        rs.getString("descripcion"),
                        rs.getString("imagen"),
                        rs.getString("url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Pelicula findByTitleAndImage(String titulo, String imagen) {
        String sql = "SELECT * FROM peliculas WHERE titulo=? AND imagen=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, titulo);
            ps.setString(2, imagen);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // Aquí debes crear un objeto Pelicula a partir de los resultados de la consulta
                return new Pelicula(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("lanzamiento"),
                        rs.getString("actor"),
                        rs.getString("autor"),
                        rs.getString("productor"),
                        rs.getString("descripcion"),
                        rs.getString("imagen"),
                        rs.getString("url")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
