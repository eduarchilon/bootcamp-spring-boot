package com.besysoft.bootcampspringboot.controller;


import com.besysoft.bootcampspringboot.dominio.Pelicula;
import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private List<Pelicula> listaPeliculas;

    public PeliculaController() {
        this.listaPeliculas = new DataDummy().getListaPeliculas();
    }

    @GetMapping()
    public List<Pelicula> obtenerPeliculas() {
        return this.listaPeliculas;
    }

    @GetMapping("/titulo/{titulo}")
    public Pelicula buscarPeliculaPorTitulo(@PathVariable String titulo) {
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getTitulo().equals(titulo)) {
                return buscada;
            }
        }
        return null;
    }

    @GetMapping("/genero/{genero}")
    public List<Pelicula> buscarPeliculasPorGenero(@PathVariable String genero) {
        List<Pelicula> listaPorGenero = new ArrayList<Pelicula>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getGenero().toString().equals(genero)) {
                listaPorGenero.add(buscada);
            }
        }
        return listaPorGenero;
    }

    @GetMapping("/fechas/")
    public List<Pelicula> buscarPeliculasPorFecha(@RequestParam String desde,
                                                  @RequestParam String hasta) {
        List<Pelicula> listaPeloculasPorFecha = new ArrayList<>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getFechaCreacion().isAfter(LocalDate.parse(desde))
                    &&
                    buscada.getFechaCreacion().isBefore(LocalDate.parse(hasta))) {
                listaPeloculasPorFecha.add(buscada);
            }
        }
        return listaPeloculasPorFecha;
    }

    @GetMapping("/calificacion/")
    public List<Pelicula> buscarPeliculasPorCalificacion(@RequestParam Double desde,
                                                         @RequestParam Double hasta) {
        List<Pelicula> listaPeloculasPorCalificacion = new ArrayList<>();
        for (Pelicula buscada : this.listaPeliculas) {
            if (buscada.getCalificacion() >= desde
                    &&
                    buscada.getCalificacion() <= hasta) {
                listaPeloculasPorCalificacion.add(buscada);
            }
        }
        return listaPeloculasPorCalificacion;
    }
}
