package pe.edu.utp.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Builder
@With
public class Comentario {
    private int id;
    private int usuario_id;
    private int pelicula_id;
    private String comentario;
    private LocalDateTime fecha_comentario;
}
