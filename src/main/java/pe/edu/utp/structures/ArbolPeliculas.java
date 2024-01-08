package pe.edu.utp.structures;

import pe.edu.utp.utils.model.Pelicula;
import java.util.ArrayList;
import java.util.List;

public class ArbolPeliculas {
    private NodoPelicula raiz;

    public void insertarPelicula(Pelicula nuevaPelicula) {
        raiz = insertarRec(raiz, nuevaPelicula);
    }
    private NodoPelicula insertarRec(NodoPelicula nodo, Pelicula nuevaPelicula) {
        if (nodo == null) {
            return new NodoPelicula(nuevaPelicula);
        }
        int comparacion = nuevaPelicula.getTitulo().compareToIgnoreCase(nodo.getPelicula().getTitulo());
        if (comparacion < 0) {
            nodo.setIzquierda(insertarRec(nodo.getIzquierda(), nuevaPelicula));
        } else if (comparacion > 0) {
            nodo.setDerecha(insertarRec(nodo.getDerecha(), nuevaPelicula));
        }
        return nodo;
    }
    public List<Pelicula> buscarPeliculas(String valorABuscar) {
        List<Pelicula> encontradas = new ArrayList<>();
        if (valorABuscar == null || valorABuscar.isEmpty()) {
            return encontradas;
        }
        buscarRec(raiz, valorABuscar.toLowerCase(), encontradas);
        return encontradas;
    }
    
    private void buscarRec(NodoPelicula nodo, String valorABuscar, List<Pelicula> encontradas) {
        if (nodo == null) {
            return;
        }
        Pelicula peliculaActual = nodo.getPelicula();
        if (peliculaActual.getTitulo().toLowerCase().contains(valorABuscar) ||
                    String.valueOf(peliculaActual.getFecha_lanzamiento()).equals(valorABuscar) ||
                    peliculaActual.getActor().toLowerCase().contains(valorABuscar) ||
                    peliculaActual.getAutor().toLowerCase().contains(valorABuscar) ||
                    peliculaActual.getProductor().toLowerCase().contains(valorABuscar)) {
            encontradas.add(peliculaActual);
        }
        buscarRec(nodo.getIzquierda(), valorABuscar, encontradas);
        buscarRec(nodo.getDerecha(), valorABuscar, encontradas);
    }
}
