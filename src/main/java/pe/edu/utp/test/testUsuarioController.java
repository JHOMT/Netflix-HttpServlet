package pe.edu.utp.test;

import pe.edu.utp.JPA.Controller.ComentarioController;
import pe.edu.utp.JPA.Controller.PeliculaController;
import pe.edu.utp.model.Comentario;
import pe.edu.utp.model.Pelicula;

import java.util.List;
import java.util.Map;

public class testUsuarioController {
    public static void main(String[] args) {
        PeliculaController peliculaController = new PeliculaController();
        ComentarioController comentarioController = new ComentarioController();

        int peliculaId = 11; // Reemplaza con el ID de la película que deseas buscar

        // Obtener la película por su ID
        Pelicula pelicula = peliculaController.findById(peliculaId);

        // Si la película existe
        if (pelicula != null) {
            // Mostrar información de la película
            System.out.println("Información de la película:");
            System.out.println("ID: " + pelicula.getId());
            System.out.println("Título: " + pelicula.getTitulo());
            System.out.println("Lanzamiento: " + pelicula.getLanzamiento());
            // ... otros detalles de la película

            // Obtener todos los comentarios de la película por cada usuario
            List<Comentario> comentarios = comentarioController.findByPeliculaId(peliculaId);

            // Si hay comentarios asociados a la película
            if (!comentarios.isEmpty()) {
                System.out.println("\nComentarios por usuario:");
                for (Comentario comentario : comentarios) {
                    // Obtener información del usuario y la película del comentario
                    Map<String, String> comentarioInfo = comentarioController.findCreatorAndMovieByComentarioId(comentario.getId());
                    if (!comentarioInfo.isEmpty()) {
                        System.out.println("Usuario: " + comentarioInfo.get("nombre_usuario"));
                        System.out.println("Pelicula: " + comentarioInfo.get("titulo_pelicula"));
                        System.out.println("Comentario: " + comentario.getComentario());
                        System.out.println("Fecha comentario: " + comentario.getFecha_comentario());
                        System.out.println("-------------------------");
                    }
                }
            } else {
                System.out.println("La película no tiene comentarios.");
            }
        } else {
            System.out.println("No se encontró la película con ID: " + peliculaId);
        }
    }
}
