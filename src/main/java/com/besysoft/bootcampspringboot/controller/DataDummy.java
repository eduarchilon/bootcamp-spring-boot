package com.besysoft.bootcampspringboot.controller;

import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.Pelicula;
import com.besysoft.bootcampspringboot.dominio.Personaje;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDummy {

    private List<Pelicula> listaPeliculas;
    private List<Personaje> listaPersonajes;

    private List<Genero> listaGeneros;

    public DataDummy() {

        Genero drama = new Genero(1L, "drama");
        Genero aventura = new Genero(2L, "aventura");

        Pelicula titanic = new Pelicula(1L, "titanic", LocalDate.parse("2020-01-01"), 10.0, drama);
        Pelicula cazafantasmas = new Pelicula(2L, "cazafantasmas", LocalDate.parse("2020-02-01"), 5.0, aventura);
        Pelicula diarioNoa = new Pelicula(3L, "el diario de Noa", LocalDate.parse("2020-03-01"), 6.0, drama);
        Pelicula looneyToones = new Pelicula(4L, "looney toones", LocalDate.parse("2020-03-01"), 2.0, aventura);

        Personaje leo = new Personaje(1L, "leonardo", 20, 60.5, "Nacio en USA", Arrays.asList(titanic));
        Personaje bill = new Personaje(2L, "bill", 30, 65.5, "Nacio en USA", Arrays.asList(titanic, cazafantasmas));
        Personaje rachel = new Personaje(3L, "rachel", 25, 55.5, "Nacio en USA", Arrays.asList(diarioNoa));
        Personaje michael = new Personaje(4L, "michael", 30, 75.5, "Nacio en USA", Arrays.asList(looneyToones));

        this.listaPeliculas = new ArrayList<>();
        this.listaPersonajes = new ArrayList<>();
        this.listaGeneros = new ArrayList<>();

        this.listaGeneros.add(drama);
        this.listaGeneros.add(aventura);

        this.listaPeliculas.add(titanic);
        this.listaPeliculas.add(cazafantasmas);
        this.listaPeliculas.add(diarioNoa);
        this.listaPeliculas.add(looneyToones);

        this.listaPersonajes.add(leo);
        this.listaPersonajes.add(bill);
        this.listaPersonajes.add(rachel);
        this.listaPersonajes.add(michael);
    }

    public List<Pelicula> getListaPeliculas() {
        return this.listaPeliculas;
    }

    public List<Personaje> getListaPersonajes() {
        return this.listaPersonajes;
    }

    public List<Genero> getListaGenero() {
        return this.listaGeneros;
    }

}
