package pe.edu.utp.servlet;

import pe.edu.utp.JPA.Controller.CategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaCategoriaController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Categoria;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static pe.edu.utp.structures.ListMoviesUsuarios.listMovies;

public class OrdenarPeliculaAdminServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Usuario usuario = IniciarSesionServlet.getUsuario();
        if (usuario == null) {
            resp.sendRedirect("/iniciar_Sesion.html");
            return;
        }
        PeliculaController peliculaController = new PeliculaController();
        List<Pelicula> peliculas = peliculaController.findAll();
        String filtroCategoria = req.getParameter("filtro_categoria");
        String filename = "src\\main\\resources\\web\\dashboard.html";
        String html = TextUTP.read(filename);
        String reporte_html = html;
        if (filtroCategoria.equals("categoria")){
            reporte_html = reporte_html.replace("${items}", ListarPeliculasPorCategoria(peliculas));
        } else if (filtroCategoria.equals("año")){
            reporte_html = reporte_html.replace("${items}", OrdenarPeliculasUsuarioServlet.OrdenarPeliculasPorAño(peliculas));
        }else{
            reporte_html = html.replace("${items}", "No se eligio ninguna opcion");
        }

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(reporte_html);
    }
    public static String ListarPeliculasPorCategoria(List<Pelicula> peliculas){
        CategoriaController categoriaController = new CategoriaController();
        PeliculaCategoriaController peliculaCategoriaController = new PeliculaCategoriaController();
        List<Categoria> categorias = categoriaController.findAll();
        StringBuilder html = new StringBuilder();
        for (Categoria categoria : categorias) {
            html.append("<h1>").append(categoria.getNombre()).append("</h1>");
            html.append("<h4>").append(categoria.getDescripcion()).append("</h4>");
            List<Pelicula> pelicula_categorias = peliculaCategoriaController.listarPeliculasPorCategoria(categoria.getId());
            html.append(listMovies(pelicula_categorias));
            html.append("\n");
        }

        return html.toString();
    }
}
