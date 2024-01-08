package pe.edu.utp.utils.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class Usuario{
    private int id;
    private String nombre;
    private String imagen;
    private String email;
    private String contrasena;
    private LocalDate fecha_registro;
    private boolean isAdmin;


    // Constructor para registro básico
    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.email = correo;
        this.contrasena = contraseña;
    }

    // Constructor completo
    public Usuario(String nombre, String imagen, String email, String contrasena, LocalDate fecha_registro, boolean isAdmin) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.email = email;
        this.contrasena = contrasena;
        this.fecha_registro = fecha_registro;
        this.isAdmin = isAdmin;
    }
}
