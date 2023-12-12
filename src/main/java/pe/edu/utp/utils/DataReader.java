package pe.edu.utp.utils;

import pe.edu.utp.model.Pelicula;
import pe.edu.utp.model.Usuario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static pe.edu.utp.data.Cargador.getPeliculas;

public class DataReader {
    public static Pelicula[] CargarPeliculas(){
        return getPeliculas();
    }

    public static Usuario[] CargarUsuarios()  {
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
                Boolean isAdmin = Boolean.parseBoolean(items[3]);
                Usuario usuarioObj = new Usuario(nombre, username, password, isAdmin);
                usuarios.add(usuarioObj);
            } else {
                System.out.println("Formato incorrecto en la linea: " + line);
            }
        }
        return usuarios.toArray(new Usuario[0]);
    }
}
