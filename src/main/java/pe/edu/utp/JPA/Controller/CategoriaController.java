package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.CategoriaDAO;
import pe.edu.utp.model.Categoria;

import java.sql.Connection;
import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        Connection connection = ConnectionFactory.getConnection();
        categoriaDAO = new CategoriaDAO(connection);
    }
    public void create(String nombre, String descripcion) {
        categoriaDAO.create(new Categoria(nombre, descripcion));
    }
    public void update(int id, String nombre, String descripcion) {
        categoriaDAO.update(new Categoria(id, nombre, descripcion));
    }
    public void delete(int id) {
        categoriaDAO.delete(id);
    }
    public List<Categoria> findAll(){
        return categoriaDAO.findAll();
    }

    public List<Categoria> findByPeliculaId(int id) {
        return categoriaDAO.findByPeliculaId(id);
    }
}
