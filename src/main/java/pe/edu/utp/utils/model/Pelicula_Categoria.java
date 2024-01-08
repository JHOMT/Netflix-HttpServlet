package pe.edu.utp.utils.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Builder
@With
public class Pelicula_Categoria {
    private int id_pelicula;
    private int id_categoria;
}
