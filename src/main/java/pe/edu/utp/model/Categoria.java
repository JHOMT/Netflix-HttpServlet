package pe.edu.utp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Builder
@With
public class Categoria {

    public enum Tipo {
        ACCION, AVENTURA, COMEDIA, DRAMA, TERROR, SUSPENSO, CIENCIA_FICCION, ANIMACION, MUSICAL, ROMANCE, FANTASIA, DOCUMENTAL, INFANTIL, OTROS
    }
    private int id;
    private String nombre;
    private String descripcion;
    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
}
