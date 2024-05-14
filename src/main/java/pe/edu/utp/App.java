package pe.edu.utp;

import jakarta.servlet.MultipartConfigElement;
import pe.edu.utp.servlet.*;
import pe.edu.utp.utils.JettyUTP;

import java.net.URL;

public class App {
    public static void main( String[] args ) throws Exception {
        String path = "src\\main\\resources\\web\\";
        JettyUTP webserver = new JettyUTP(8081, path);

        webserver.addServlet(AcercaServlet.class,"/acerca");
        webserver.addServlet(ContactoServlet.class,"/contacto");

        webserver.addServlet(ListarPeliculasServlet.class,"/listar");
        webserver.addServlet(DashboardPeliculasServlet.class,"/dashboard");
        webserver.addServlet(OrdenarPeliculasUsuarioServlet.class,"/DashboardOrdenar");

        webserver.addServlet(ListarUsuariosServlet.class,"/usuarios");
        webserver.addServlet(RegistrarUsuarioServlet.class,"/registrar").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\web\\img\\usuarios"));
        webserver.addServlet(RegistrarPeliculaServlet.class,"/registrarPelicula").getRegistration().setMultipartConfig(new MultipartConfigElement("src\\main\\resources\\web\\img\\peliculas"));
        webserver.addServlet(IniciarSesionServlet.class,"/login"); // Iniciar sesion
        webserver.addServlet(PeliculaDetalleServlet.class, "/detalle"); // Detalle de cada pelicula
        webserver.addServlet(BuscarPeliculaUsuarioServlet.class, "/buscar"); // Buscar pelicula usuario
        webserver.addServlet(BuscarPeliculaDasboardServlet.class, "/DashboardSearch"); // Buscar pelicula administrador
        webserver.addServlet(BuscarUsuarioServlet.class, "/buscarUsuario"); // Buscar usuario administrador

        webserver.addServlet(ComentarServlet.class, "/comentar"); // Comentar pelicula
        URL myURL = new URL("http://localhost:8081"); // URL de la aplicacion
        System.out.println("*********************************************************");
        System.out.println("CLICK AQUI PARA ABRIR LA APLICACION:" + myURL); // Imprimir URL
        System.out.println("*********************************************************");
        webserver.start();
    }

}
