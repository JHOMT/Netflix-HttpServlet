package pe.edu.utp.test;

import junit.framework.TestCase;
import pe.edu.utp.model.Pelicula;
import pe.edu.utp.presentacion_2.Cargador_2;

public class CragaUsariosTest extends TestCase {

    public void testMain() {
        Pelicula[] peliculas = Cargador_2.cargarPeliculas().toArray(new Pelicula[0]);
        System.out.println(peliculas);
    }
}