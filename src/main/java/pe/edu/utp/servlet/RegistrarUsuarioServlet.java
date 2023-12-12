package pe.edu.utp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.model.Usuario;
import pe.edu.utp.utils.TextUTP;

import java.io.IOException;

public class RegistrarUsuarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String data = "src\\main\\java\\pe\\edu\\utp\\data\\usuarios.txt";
        
        String nombre = req.getParameter("nombre");
        String usuario = req.getParameter("correo");
        String contraseña = req.getParameter("contrasena");
        String adminParam = req.getParameter("admin");

        boolean isadmin = "on".equals(adminParam);
        
        
        if (usuarioExiste(usuario, data)) {
            resp.sendRedirect("src\\main\\resources\\web\\error.html");
            return;
        }

        try {
            if (isadmin){
                String formatoData = String.format("\n%s,%s,%s,%s", nombre, usuario, contraseña,isadmin);
                TextUTP.append(formatoData, data);
            }else {
                String formatoData = String.format("\n%s,%s,%s,%s", nombre, usuario, contraseña, false);
                TextUTP.append(formatoData, data);
            }
            resp.sendRedirect("/usuarios");
        } catch (IllegalArgumentException e) {
            String filenameError = "src\\main\\resources\\web\\error.html";
            String htmlError = TextUTP.read(filenameError);
            resp.getWriter().println(htmlError.replace("${error}", e.getMessage()));
        }
    }

    private boolean usuarioExiste(String usuario, String dataFilePath) throws IOException {
        String usuariosData = TextUTP.read(dataFilePath);
        String[] lineas = usuariosData.split("\n");
        for (String linea : lineas) {
            String[] partes = linea.split(",");
            if (partes.length > 1 && partes[1].equals(usuario)) {
                return true;
            }
        }
        return false;
    }
}
