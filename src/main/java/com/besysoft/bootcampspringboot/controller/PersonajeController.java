package com.besysoft.bootcampspringboot.controller;


import com.besysoft.bootcampspringboot.dominio.Genero;
import com.besysoft.bootcampspringboot.dominio.Pelicula;
import com.besysoft.bootcampspringboot.dominio.Personaje;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Personaje>> obtenerPersonajes() {
        return new ResponseEntity<List<Personaje>>(this.listaPersonajes, HttpStatus.ACCEPTED);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Personaje> buscarPersonajePorNombre(@PathVariable String nombre) {
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getNombre().equals(nombre)) {
                return new ResponseEntity<Personaje>(buscado, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    @GetMapping("/edad/{edad}")
    public ResponseEntity<List<Personaje>> buscarPersonajesPorEdad(@PathVariable Integer edad) {
        List<Personaje> listaPoEdad = new ArrayList<>();
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getEdad() == edad) {
                listaPoEdad.add(buscado);
            }
        }
        return new ResponseEntity<List<Personaje>>(listaPoEdad, HttpStatus.ACCEPTED);
    }

    @GetMapping("/edad/")
    public ResponseEntity<List<Personaje>> buscarPersonajesPorRangoDeEdad(@RequestParam Integer desde,
                                                          @RequestParam Integer hasta) {
        List<Personaje> listaPoEdad = new ArrayList<>();
        for (Personaje buscado : this.listaPersonajes) {
            if (buscado.getEdad() >= desde
                    &&
                    buscado.getEdad() <= hasta) {
                listaPoEdad.add(buscado);
            }
        }
        return new ResponseEntity<List<Personaje>>(listaPoEdad, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Personaje> agregarPersonaje(@RequestBody Personaje personaje) {
        personaje.setId((long) (this.listaPersonajes.size() + 1));
        this.listaPersonajes.add(personaje);
        return new ResponseEntity<Personaje>(personaje, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personaje> modificarPersonaje(@RequestBody Personaje personaje,
                                        @PathVariable Long id) {
        for (Personaje buscado :
                this.listaPersonajes) {
            if (buscado.getId() == id) {
                buscado.setNombre(personaje.getNombre());
                buscado.setEdad(personaje.getEdad());
                buscado.setHistoria(personaje.getHistoria());
                buscado.setPeso(personaje.getPeso());
                buscado.setPeliculas(personaje.getPeliculas());
                return new ResponseEntity<Personaje>(buscado, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
