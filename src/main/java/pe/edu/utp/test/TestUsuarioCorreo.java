package pe.edu.utp.test;

import pe.edu.utp.data.Cargador;
import pe.edu.utp.metodos.OrdenamientoUsuarios.OrdenamientoCorreo;
import pe.edu.utp.model.Usuario;

import java.io.IOException;

public class TestUsuarioCorreo {
    public static void main(String[] args) throws IOException {
        Usuario[] usuarios = Cargador.cargarUsuarios();
        Usuario[] usuariosOrdenadosNombre = OrdenamientoCorreo.mergeSort(usuarios);
        String correo = "yasm146@gmail.com", contraseña = "yasmi284";
        int numero = binarySearch(correo, contraseña);
        if (numero != 1){
            System.out.println(usuariosOrdenadosNombre[numero]);
        }else {
            System.out.println("No encontrado " + correo);
        }

    }
    public static int binarySearch( String correo, String contraseña) throws IOException {
        Usuario[] usuarios = Cargador.cargarUsuarios();
        Usuario[] arreglo = OrdenamientoCorreo.mergeSort(usuarios);
        int izquierda = 0;
        int derecha = arreglo.length - 1;
        while (izquierda <= derecha) {
            int medio = izquierda + (derecha - izquierda) / 2;
            int comparacionNombre = arreglo[medio].getUsername().compareTo(correo);
            int comparacionContraseña = arreglo[medio].getPassword().compareTo(contraseña);
            if (comparacionNombre == 0 && comparacionContraseña == 0) {
                return medio;
            } else if (comparacionNombre < 0 || (comparacionNombre == 0 && comparacionContraseña < 0)) {
                izquierda = medio + 1;
            } else {
                derecha = medio - 1;
            }
        }
        return -1;
    }
}
