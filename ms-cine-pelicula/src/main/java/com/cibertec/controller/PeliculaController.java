package com.cibertec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.model.Pelicula;
import com.cibertec.service.PeliculaService;

@RestController
@RequestMapping("cine/peliculas")
public class PeliculaController {
	
	@Autowired
	private PeliculaService serv;
	
	@GetMapping
    public ResponseEntity<List<Pelicula>> listar() {
        return new ResponseEntity<>(serv.lstPeliculas(), HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<Pelicula> buscarPorId(@PathVariable("id") int id) {
        return serv.buscarPeliPorId(id)
                .map(peli -> new ResponseEntity<>(peli, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	@PostMapping
    public ResponseEntity<Pelicula> registrar(@RequestBody Pelicula peli) {
        Pelicula nuevaPeli = serv.registrar(peli);
        return new ResponseEntity<>(nuevaPeli, HttpStatus.CREATED);
    }
	
	@PutMapping
    public ResponseEntity<Pelicula> actualizar(@RequestBody Pelicula peli) {
        Pelicula peliActualizada = serv.actualizar(peli);
        return new ResponseEntity<>(peliActualizada, HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int id) {
        if (serv.buscarPeliPorId(id).isPresent()) {
            serv.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
