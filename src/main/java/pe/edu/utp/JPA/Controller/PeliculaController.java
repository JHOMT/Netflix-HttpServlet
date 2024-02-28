package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.PeliculaDAO;
import pe.edu.utp.model.Pelicula;

import java.sql.Connection;
import java.util.List;

public class PeliculaController {
    private PeliculaDAO peliculaDAO;

    public PeliculaController() {
        Connection connection = ConnectionFactory.getConnection();
        peliculaDAO = new PeliculaDAO(connection);
    }
    public boolean create(Pelicula pelicula) {
        return peliculaDAO.create(new Pelicula(pelicula.getTitulo(), pelicula.getLanzamiento(), pelicula.getActor(), pelicula.getAutor(), pelicula.getProductor(), pelicula.getDescription(), pelicula.getImagen(), pelicula.getUrl()));
    }
    public boolean update(int id, String titulo, int fecha_lanzamiento, String actor, String autor, String productor, String description, String imagen, String url) {
        return peliculaDAO.update(new Pelicula(id, titulo, fecha_lanzamiento, actor, autor, productor, description, imagen, url));
    }
    public boolean delete(int id) {
        return peliculaDAO.delete(id);
    }
    public Pelicula finByTitle(String titulo) {
        return peliculaDAO.finByTitle(titulo);
    }
    public List<Pelicula> findAll() {
        return peliculaDAO.findAll();
    }
    public List<Pelicula> findByAll(String titulo) {
        return peliculaDAO.findByAll(titulo);
    }

    public Pelicula findById(int peliculaId) {
        return peliculaDAO.findById(peliculaId);
    }

    public Pelicula findByTitleAndImage(String titulo, String imagen) {
        return peliculaDAO.findByTitleAndImage(titulo, imagen);
    }
}
