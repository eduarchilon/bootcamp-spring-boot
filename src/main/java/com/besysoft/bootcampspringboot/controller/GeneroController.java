package com.besysoft.bootcampspringboot.controller;

import com.besysoft.bootcampspringboot.dominio.Genero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private List<Genero> listaGeneros;

    public GeneroController() {
        this.listaGeneros = new DataDummy().getListaGenero();
    }

    @GetMapping()
    public ResponseEntity<List<Genero>> obtenerGeneros() {
        return new ResponseEntity<List<Genero>>(this.listaGeneros, HttpStatus.ACCEPTED);
    }

    @PostMapping()
    public ResponseEntity<Genero> agregarGenero(@RequestBody Genero genero) {
        genero.setId((long) (this.listaGeneros.size() + 1));
        this.listaGeneros.add(genero);
        return new ResponseEntity<Genero>(genero, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genero> modificarGenero(@RequestBody Genero genero, @PathVariable Long id) {
        for (Genero buscado : this.listaGeneros) {
            if (buscado.getId() == id) {
                buscado.setNombre(genero.getNombre());
                return new ResponseEntity<Genero>(buscado, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

}
