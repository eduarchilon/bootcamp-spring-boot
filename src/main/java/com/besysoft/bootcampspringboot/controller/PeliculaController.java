package com.besysoft.bootcampspringboot.controller;

import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.Pelicula;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private List<Pelicula> listaPeliculas;

    public PeliculaController() {
        this.listaPeliculas = new DataDummy().getListaPeliculas();
    }

    @GetMapping()
    public ResponseEntity<List<Pelicula>> obtenerPeliculas() {
        return new ResponseEntity<List<Pelicula>>(this.listaPeliculas, HttpStatus.ACCEPTED);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<Pelicula> buscarPeliculaPorTitulo(@PathVariable String titulo) {
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getTitulo().equals(titulo)) {
                return new ResponseEntity<Pelicula>(buscada, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorGenero(@PathVariable String genero) {
        List<Pelicula> listaPorGenero = new ArrayList<Pelicula>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getGenero().toString().equals(genero)) {
                listaPorGenero.add(buscada);
            }
        }
        return new ResponseEntity<List<Pelicula>>(listaPorGenero, HttpStatus.ACCEPTED);
    }

    @GetMapping("/fechas/")
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorFecha(@RequestParam String desde,
                                                                  @RequestParam String hasta) {
        List<Pelicula> listaPeloculasPorFecha = new ArrayList<>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getFechaCreacion().isAfter(LocalDate.parse(desde))
                    &&
                    buscada.getFechaCreacion().isBefore(LocalDate.parse(hasta))) {
                listaPeloculasPorFecha.add(buscada);
            }
        }
        return new ResponseEntity<List<Pelicula>>(listaPeloculasPorFecha, HttpStatus.ACCEPTED);
    }

    @GetMapping("/calificacion/")
    public ResponseEntity<List<Pelicula>> buscarPeliculasPorCalificacion(@RequestParam Double desde,
                                                                         @RequestParam Double hasta) {
        List<Pelicula> listaPeloculasPorCalificacion = new ArrayList<>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getCalificacion() >= desde
                    &&
                    buscada.getCalificacion() <= hasta) {
                listaPeloculasPorCalificacion.add(buscada);
            }
        }
        return new ResponseEntity<List<Pelicula>>(listaPeloculasPorCalificacion, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Pelicula> agregarPelicula(@RequestBody Pelicula pelicula) {
        pelicula.setId((long) (this.listaPeliculas.size() + 1));
        this.listaPeliculas.add(pelicula);
        return new ResponseEntity<Pelicula>(pelicula, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> modificarPelicula(@RequestBody Pelicula pelicula,
                                      @PathVariable Long id) {
        for (Pelicula buscada :
                this.listaPeliculas) {
            if (buscada.getId() == id) {
                buscada.setTitulo(pelicula.getTitulo());
                buscada.setGenero(pelicula.getGenero());
                buscada.setCalificacion(pelicula.getCalificacion());
                buscada.setFechaCreacion(pelicula.getFechaCreacion());
                return new ResponseEntity<Pelicula>(buscada, HttpStatus.ACCEPTED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
    }
}
