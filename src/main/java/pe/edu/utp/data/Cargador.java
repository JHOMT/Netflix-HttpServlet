package pe.edu.utp.data;

import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Cargador {

    public static Pelicula[] cargarPeliculas(){
        return getPeliculas();
    }

    public static Pelicula[] getPeliculas() {
        String filename = "src\\main\\java\\pe\\edu\\utp\\data\\peliculas.txt";
        List<Pelicula> peliculas = new ArrayList<>();
        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            String[] items = line.split("˙"); //
            if (items.length == 8) {
                String nombre = items[0];
                int año = Integer.parseInt(items[1]);
                String actor = items[2];
                String autor = items[3];
                String productor = items[4];
                String decripciom = items[5];
                String imagen = items[6];
                String video = items[7];
                Pelicula peliculaObj = new Pelicula(nombre, año, actor, autor, productor, decripciom, imagen, video);
                peliculas.add(peliculaObj);
            } else {
                System.out.println("Formato incorrecto de liena: " + line);
            }
        }
        return peliculas.toArray(new Pelicula[0]);
    }

    public static Usuario[] cargarUsuarios()  {
        String filename = "src\\main\\java\\pe\\edu\\utp\\data\\usuarios.txt";
        List<Usuario> usuarios = new ArrayList<>();
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String line : lines) {
            String[] items = line.split(",");
            if (items.length == 4) {
                String nombre = items[0].trim();
                String username = items[1].trim();
                String password = items[2].trim();
                boolean isAdmin = Boolean.parseBoolean(items[3]);
                Usuario usuarioObj = new Usuario(nombre, username, password, isAdmin);
                usuarios.add(usuarioObj);
            } else {
                System.out.println("Formato incorrecto en la linea: " + line);
            }
        }
        return usuarios.toArray(new Usuario[0]);
    }

}
