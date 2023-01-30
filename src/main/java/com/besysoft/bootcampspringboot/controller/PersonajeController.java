package com.besysoft.bootcampspringboot.controller;


import com.besysoft.bootcampspringboot.dominio.Personaje;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personajes")
public class PersonajeController {

    private List<Personaje> listaPersonajes;

    public PersonajeController() {
        this.listaPersonajes = new DataDummy().getListaPersonajes();
    }

    @GetMapping()
    public List<Personaje> obtenerPersonajes() {
        return this.listaPersonajes;
    }

    @GetMapping("/nombre/{nombre}")
    public Personaje buscarPersonajePorNombre(@PathVariable String nombre) {
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getNombre().equals(nombre)) {
                return buscado;
            }
        }
        return null;
    }

    @GetMapping("/edad/{edad}")
    public List<Personaje> buscarPersonajesPorEdad(@PathVariable Integer edad) {
        List<Personaje> listaPoEdad = new ArrayList<>();
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getEdad() == edad) {
                listaPoEdad.add(buscado);
            }
        }
        return listaPoEdad;
    }

    @GetMapping("/edad/")
    public List<Personaje> buscarPersonajesPorRangoDeEdad(@RequestParam Integer desde,
                                                          @RequestParam Integer hasta) {
        List<Personaje> listaPoEdad = new ArrayList<>();
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getEdad() >= desde
                    &&
                    buscado.getEdad() <= hasta  ) {
                listaPoEdad.add(buscado);
            }
        }
        return listaPoEdad;
    }
}
