package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.UsuarioDAO;
import pe.edu.utp.model.Usuario;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        Connection connection = ConnectionFactory.getConnection();
        usuarioDAO = new UsuarioDAO(connection);
    }
    public boolean insert(String nombre, String imagen, String correo, String contraseña, LocalDate fecha_registro, boolean isAdmin) {
        return usuarioDAO.insert(new Usuario(nombre, imagen, correo, contraseña, fecha_registro, isAdmin));
    }
    public boolean update(int id, String nombre, String imagen,  String correo, String contraseña, LocalDate fecha_registro,  boolean isAdmin) {
        return usuarioDAO.update(new Usuario(id, nombre, imagen, correo, contraseña, fecha_registro, isAdmin));
    }
    public boolean delete(int id) {
        return usuarioDAO.delete(id);
    }
    public List<Usuario> findAll() {
        return usuarioDAO.findAll();
    }
    public Usuario login(String correo, String contraseña) {
        return usuarioDAO.login(correo, contraseña);
    }
    public List<Usuario> findByAll(String nombre) {
        return usuarioDAO.findByAll(nombre);
    }

    public Usuario findById(int usuarioId) {
        return usuarioDAO.findById(usuarioId);
    }

    public boolean existUser(String usuario) {
        return usuarioDAO.findByEmail(usuario);
    }

    public boolean insert(Usuario nuevoUsuario) {
        return usuarioDAO.insert(nuevoUsuario);
    }
}
