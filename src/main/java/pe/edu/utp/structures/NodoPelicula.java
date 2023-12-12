package pe.edu.utp.structures;

import lombok.*;
import pe.edu.utp.model.Pelicula;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class NodoPelicula {
    private Pelicula pelicula;
    private NodoPelicula izquierda;
    private NodoPelicula derecha;
    
    public NodoPelicula(Pelicula nuevaPelicula) {
        this.pelicula = nuevaPelicula;
        this.izquierda = null;
        this.derecha = null;
    }
}
