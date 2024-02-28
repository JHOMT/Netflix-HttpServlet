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
public class Pelicula {
    private int id;
    private String titulo;
    private int lanzamiento;
    private String actor;
    private String autor;
    private String productor;
    private String description;
    private String imagen;
    private String url;

    public Pelicula(String titulo, int lanzamiento, String actor, String autor, String productor, String description, String imagen, String url) {
        this.titulo = titulo;
        this.lanzamiento = lanzamiento;
        this.actor = actor;
        this.autor = autor;
        this.productor = productor;
        this.description = description;
        this.imagen = imagen;
        this.url = url;
    }

}
