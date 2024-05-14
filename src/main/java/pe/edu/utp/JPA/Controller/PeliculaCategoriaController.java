package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.PeliculaCategoriaDAO;
import pe.edu.utp.model.Pelicula;

import java.sql.Connection;
import java.util.List;

public class PeliculaCategoriaController {
    private PeliculaCategoriaDAO peliculaCategoriaDAO;

    public PeliculaCategoriaController() {
        Connection connection = ConnectionFactory.getConnection();
        peliculaCategoriaDAO = new PeliculaCategoriaDAO(connection);
    }
    public void registrarPeliculaCategoria(int id_pelicula, int id_categoria) {
        peliculaCategoriaDAO.registrarPeliculaCategoria(id_pelicula, id_categoria);
    }
    public List<Pelicula> listarPeliculasPorCategoria(int id_categoria) {
        return peliculaCategoriaDAO.listarPeliculasPorCategoria(id_categoria);
    }
}
