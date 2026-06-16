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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cibertec.dto.SalaDTO;
import com.cibertec.model.Cine;
import com.cibertec.model.Sala;
import com.cibertec.service.SalaService;

@RestController
@RequestMapping("cine/salas")
public class SalaController {

	@Autowired
    private SalaService serv;

    @GetMapping
    public ResponseEntity<List<Sala>> listar() {
        return new ResponseEntity<>(serv.lstSala(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sala> buscarPorId(@PathVariable("id") int id) {
        return serv.buscarSalaPorId(id)
                .map(sala -> new ResponseEntity<>(sala, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Sala> registrar(@RequestBody SalaDTO dto) {
        Sala sala = new Sala();
        sala.setNumSala(dto.getNumSala());
        sala.setCapacidad(dto.getCapacidad());
        sala.setTipoPantalla(dto.getTipoPantalla());
        
        if (dto.getIdCine() != null) {
            Cine cine = new Cine();
            cine.setIdCine(dto.getIdCine());
            sala.setCine(cine);
        }
        
        return new ResponseEntity<>(serv.registrar(sala), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Sala> actualizar(@RequestBody SalaDTO dto) {
        Sala sala = new Sala();
        sala.setIdSala(dto.getIdSala());
        sala.setNumSala(dto.getNumSala());
        sala.setCapacidad(dto.getCapacidad());
        sala.setTipoPantalla(dto.getTipoPantalla());
        
        if (dto.getIdCine() != null) {
            Cine cine = new Cine();
            cine.setIdCine(dto.getIdCine());
            sala.setCine(cine);
        }
        
        return new ResponseEntity<>(serv.actualizar(sala), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int id) {
        if (serv.buscarSalaPorId(id).isPresent()) {
            serv.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<SalaDTO>> buscarSalasPorCine(@RequestParam("idCine") int idCine) {
        return new ResponseEntity<>(serv.listarSalasPorIdCine(idCine), HttpStatus.OK);
    }
}