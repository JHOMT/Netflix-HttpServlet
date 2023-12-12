package pe.edu.utp.test;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OrdenamientoUsuarios.OrdenamientoUsariosNombre;
import pe.edu.utp.model.Usuario;

import java.io.IOException;

public class TestOdenamientoUsuarios {
    public static void main(String[] args) throws IOException {
        Usuario[] usuarios = Cargador.cargarUsuarios();
        Usuario[] usuariosOrdenadosNombre = OrdenamientoUsariosNombre.mergeSort(usuarios);
        for (Usuario usuario : usuariosOrdenadosNombre) {
            System.out.println(usuario);
        }
    }
}
