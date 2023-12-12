package pe.edu.utp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class Pelicula implements Comparable<Pelicula>{
    private String name;
    private int año;
    private String actor;
    private String autor;
    private String productor;
    private String description;
    private String imagen;
    private String videoLink;

    @Override
    public String toString() {
        return String.format( "|%-30.30s| %-4.4s | %-15.15s | %-15.15s | %-15.15s | %-30.30s | %-15.15s |",name ,año ,actor , autor, productor, description, imagen);
    }
    @Override
    public int compareTo(Pelicula o) {
        return this.name.compareTo(o.name);
    }
}
