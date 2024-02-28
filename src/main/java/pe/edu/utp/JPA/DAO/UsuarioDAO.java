package pe.edu.utp.JPA.DAO;

import pe.edu.utp.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean insert(Usuario usuario) {
        String sql = "INSERT INTO usuarios(nombre, imagen, email, contrasena, fecha_registro, is_admin) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getImagen());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getContrasena());
            ps.setDate(5, Date.valueOf(usuario.getFecha_registro()));
            ps.setBoolean(6, usuario.isAdmin());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, clave = ?, is_admin = ? WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getContrasena());
            ps.setBoolean(3, usuario.isAdmin());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean delete(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public List<Usuario> findAll() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeQuery();
            while (ps.getResultSet().next()) {
                int id = ps.getResultSet().getInt("id");
                String nombre = ps.getResultSet().getString("nombre");
                String imagen = ps.getResultSet().getString("imagen");
                String email = ps.getResultSet().getString("email");
                String contrasena = ps.getResultSet().getString("contrasena");
                LocalDate fecha_registro = ps.getResultSet().getDate("fecha_registro").toLocalDate();
                boolean is_admin = ps.getResultSet().getBoolean("is_admin");
                Usuario usuario = new Usuario(id, nombre, imagen, email, contrasena, fecha_registro, is_admin);
                usuarios.add(usuario);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return usuarios;
    }
    public Usuario login(String email, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasena = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, contrasena);
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                int id = ps.getResultSet().getInt("id");
                String nombre = ps.getResultSet().getString("nombre");
                String imagen = ps.getResultSet().getString("imagen");
                LocalDate fecha_registro = ps.getResultSet().getDate("fecha_registro").toLocalDate();
                boolean is_admin = ps.getResultSet().getBoolean("is_admin");
                Usuario usuario = new Usuario(id, nombre, imagen, email, contrasena, fecha_registro, is_admin);
                ps.close();
                return usuario;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    public List<Usuario> findByAll(String filter) {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE nombre LIKE ? OR email LIKE ? OR contrasena LIKE ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            String likeFilter = "%" + filter + "%";
            for (int i = 1; i <= 3; i++) {
                ps.setString(i, likeFilter);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("imagen"),
                        rs.getString("email"),
                        rs.getString("contrasena"),
                        rs.getDate("fecha_registro").toLocalDate(),
                        rs.getBoolean("is_admin")
                );
                usuarios.add(usuario);
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return usuarios;
    }

    public Usuario findById(int id) {
        String sql = "SELECT * FROM USUARIOS WHERE ID = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String imagen = rs.getString("imagen");
                String email = rs.getString("email");
                String contrasena = rs.getString("contrasena");
                LocalDate fecha_registro = rs.getDate("fecha_registro").toLocalDate();
                boolean is_admin = rs.getBoolean("is_admin");
                Usuario usuario = new Usuario(id, nombre, imagen, email, contrasena, fecha_registro, is_admin);
                ps.close();
                return usuario;
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el usuario por id: " + e.getMessage()); // Lanzar la excepción en caso de error
        }
        return null;
    }

    public boolean findByEmail(String usuario) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.executeQuery();
            if (ps.getResultSet().next()) {
                ps.close();
                return true;
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
