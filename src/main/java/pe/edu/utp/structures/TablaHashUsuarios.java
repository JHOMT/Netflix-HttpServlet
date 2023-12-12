package pe.edu.utp.structures;

import pe.edu.utp.model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablaHashUsuarios {
    private Map<String, Usuario> mapaUsuarios;

    public TablaHashUsuarios() {
        this.mapaUsuarios = new HashMap<>();
    }

    public void agregarUsuario(String hashCode, Usuario usuario) {
        mapaUsuarios.put(hashCode, usuario);
    }

    public List<Usuario> buscarNombreUsuario(String nombreUsuario) {
        List<Usuario> usuariosEncontrados = new ArrayList<>();
        for (Usuario usuario : mapaUsuarios.values()) {
            if (compararNombres(usuario.getNombre(), nombreUsuario)) {
                usuariosEncontrados.add(usuario);
            }
        }
        return usuariosEncontrados;
    }

    private boolean compararNombres(String nombreGuardado, String nombreBuscado) {
        return nombreGuardado.replaceAll("\\s+", "").equalsIgnoreCase(nombreBuscado.replaceAll("\\s+", ""));
    }

    public Usuario buscarUsuario(String username) {
        return mapaUsuarios.get(username);
    }
}
