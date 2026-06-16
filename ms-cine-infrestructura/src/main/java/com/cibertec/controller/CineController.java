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

import com.cibertec.dto.CineDTO;
import com.cibertec.model.Cine;
import com.cibertec.service.CineService;

@RestController
@RequestMapping("cine/cines")
public class CineController {
	
	@Autowired
	private CineService serv;
	
	@GetMapping
    public ResponseEntity<List<Cine>> listar() {
        return new ResponseEntity<>(serv.lstCine(), HttpStatus.OK);
    }

	@GetMapping("/{id}")
    public ResponseEntity<Cine> buscarPorId(@PathVariable("id") int id) {
        return serv.buscarCinePorId(id)
                .map(cine -> new ResponseEntity<>(cine, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
	
	@PostMapping
    public ResponseEntity<Cine> registrar(@RequestBody CineDTO dto) {
        Cine cine = new Cine();
        cine.setNombreCine(dto.getNombreCine());
        cine.setDireccion(dto.getDireccion());
        cine.setCiudad(dto.getCiudad());
        
        return new ResponseEntity<>(serv.registrar(cine), HttpStatus.CREATED);
    }
	
	@PutMapping
    public ResponseEntity<Cine> actualizar(@RequestBody CineDTO dto) {
        Cine cine = new Cine();
        cine.setIdCine(dto.getIdCine());
        cine.setNombreCine(dto.getNombreCine());
        cine.setDireccion(dto.getDireccion());
        cine.setCiudad(dto.getCiudad());
        
        return new ResponseEntity<>(serv.actualizar(cine), HttpStatus.OK);
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int id) {
        if (serv.buscarCinePorId(id).isPresent()) {
            serv.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}