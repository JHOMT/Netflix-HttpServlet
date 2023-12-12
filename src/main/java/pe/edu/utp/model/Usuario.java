package pe.edu.utp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Usuario implements Comparable<Usuario>{

    private String nombre;
    private String username;
    private String password;
    private boolean isAdmin;

    public Usuario(String correo, String contraseña) {
        this.username = correo;
        this.password = contraseña;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
    @Override
    public int compareTo(Usuario otroUsuario) {
        return this.nombre.compareTo(otroUsuario.nombre);
    }
}
