package pe.edu.utp;

import pe.edu.utp.servlet.*;
import pe.edu.utp.utils.JettyUTP;

import java.net.URL;

public class App {
    public static void main( String[] args ) throws Exception {
        String path = "src\\main\\resources\\web\\";
        JettyUTP webserver = new JettyUTP(8082, path,"Movies");
        webserver.addServlet(ListarPeliculasServlet.class,"/listar");
        webserver.addServlet(ListarUsuariosServlet.class,"/usuarios");
        webserver.addServlet(DashboardPeliculasServlet.class,"/dashboard");
        webserver.addServlet(RegistrarUsuarioServlet.class,"/registrar");
        webserver.addServlet(RegistrarPeliculaServlet.class,"/registrarPelicula");
        webserver.addServlet(IniciarSesionServlet.class,"/login");
        webserver.addServlet(PeliculaDetalleServlet.class, "/detalle");
        webserver.addServlet(BuscarPeliculaServlet.class, "/buscar");
        webserver.addServlet(BuscarUsuarioServlet.class, "/buscarUsuario");
        URL myURL = new URL("http://localhost:8082");
        System.out.println("*********************************************************");
        System.out.println("CLICK AQUI PARA ABRIR LA APLICACION:" + myURL);
        System.out.println("*********************************************************");
        webserver.start();
    }
}
