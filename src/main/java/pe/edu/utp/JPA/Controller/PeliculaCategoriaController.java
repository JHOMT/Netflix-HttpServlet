package pe.edu.utp.JPA.Controller;

import pe.edu.utp.JPA.Factory.ConnectionFactory;
import pe.edu.utp.JPA.DAO.PeliculaCategoriaDAO;

import java.sql.Connection;

public class PeliculaCategoriaController {
    private PeliculaCategoriaDAO peliculaCategoriaDAO;

    public PeliculaCategoriaController() {
        Connection connection = ConnectionFactory.getConnection();
        peliculaCategoriaDAO = new PeliculaCategoriaDAO(connection);
    }
    public void registrarPeliculaCategoria(int id_pelicula, int id_categoria) {
        peliculaCategoriaDAO.registrarPeliculaCategoria(id_pelicula, id_categoria);
    }
}
