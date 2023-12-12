package pe.edu.utp.test;

import pe.edu.utp.model.Usuario;
import pe.edu.utp.structures.TablaHashUsuarios;
import pe.edu.utp.utils.DataReader;

public class MainHashUsuarios {
    public static void main(String[] args) {
        // Crear una instancia de TablaHashUsuarios
        TablaHashUsuarios tablaUsuarios = new TablaHashUsuarios();

        Usuario[] usuarios = DataReader.CargarUsuarios();
        for (Usuario usuario : usuarios) {
            tablaUsuarios.agregarUsuario(usuario.getUsername(), usuario);
        }

        // Realizar la búsqueda de un usuario por su nombre de usuario
        Usuario usuarioEncontrado = tablaUsuarios.buscarUsuario("erick34@gmail.com");

        // Verificar si se encontró el usuario y mostrar los detalles
        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado: " + usuarioEncontrado);
        } else {
            System.out.println("Usuario no encontrado");
        }
    }
}
