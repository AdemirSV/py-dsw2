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

import com.cibertec.dto.AsientoDTO;
import com.cibertec.model.Asiento;
import com.cibertec.model.Sala;
import com.cibertec.service.AsientoService;

@RestController
@RequestMapping("cine/asientos")
public class AsientoController {

	@Autowired
    private AsientoService serv;

    @GetMapping
    public ResponseEntity<List<Asiento>> listar() {
        return new ResponseEntity<>(serv.lstAsiento(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asiento> buscarPorId(@PathVariable("id") int id) {
        return serv.buscarAsientoPorId(id)
                .map(asiento -> new ResponseEntity<>(asiento, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Asiento> registrar(@RequestBody AsientoDTO dto) {
        Asiento asiento = new Asiento();
        asiento.setFila(dto.getFila());
        asiento.setNumero(dto.getNumero());
        asiento.setTipoAsiento(dto.getTipoAsiento());
        
        if (dto.getIdSala() != null) {
            Sala sala = new Sala();
            sala.setIdSala(dto.getIdSala());
            asiento.setSala(sala);
        }
        
        return new ResponseEntity<>(serv.registrar(asiento), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Asiento> actualizar(@RequestBody AsientoDTO dto) {
        Asiento asiento = new Asiento();
        asiento.setIdAsiento(dto.getIdAsiento());
        asiento.setFila(dto.getFila());
        asiento.setNumero(dto.getNumero());
        asiento.setTipoAsiento(dto.getTipoAsiento());
        
        if (dto.getIdSala() != null) {
            Sala sala = new Sala();
            sala.setIdSala(dto.getIdSala());
            asiento.setSala(sala);
        }
        
        return new ResponseEntity<>(serv.actualizar(asiento), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int id) {
        if (serv.buscarAsientoPorId(id).isPresent()) {
            serv.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<AsientoDTO>> buscarAsientosPorSala(@RequestParam("idSala") int idSala) {
        return new ResponseEntity<>(serv.listarAsientosPorIdSala(idSala), HttpStatus.OK);
    }
}