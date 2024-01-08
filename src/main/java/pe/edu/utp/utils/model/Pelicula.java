package pe.edu.utp.utils.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Builder
@With
public class Pelicula {
    private int id;
    private String titulo;
    private int fecha_lanzamiento;
    private String actor;
    private String autor;
    private String productor;
    private String description;
    private String imagen;
    private String url;

    public Pelicula(String titulo, int fecha_lanzamiento, String actor, String autor, String productor, String description, String imagen, String url) {
        this.titulo = titulo;
        this.fecha_lanzamiento = fecha_lanzamiento;
        this.actor = actor;
        this.autor = autor;
        this.productor = productor;
        this.description = description;
        this.imagen = imagen;
        this.url = url;
    }

}
