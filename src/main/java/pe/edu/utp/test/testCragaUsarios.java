package pe.edu.utp.test;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.model.Usuario;

import java.io.IOException;

public class testCragaUsarios {
    public static void main(String[] args) throws IOException {
        Usuario[] usuarios = Cargador.cargarUsuarios();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
}
