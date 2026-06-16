package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.SalaDTO;
import com.cibertec.model.Sala;
import com.cibertec.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository repo;
	
	public List<Sala> lstSala(){
		return repo.findAll();
	}

	public Optional<Sala> buscarSalaPorId(int id){
		return repo.findById(id);
	}
	
	public Sala registrar(Sala sala) {
		return repo.save(sala);
	}
	
	public Sala actualizar(Sala salaModificada) {
	    return repo.findById(salaModificada.getIdSala())
	        .map(salaExistente -> {
	            if (salaModificada.getNumSala() != null) {
	                salaExistente.setNumSala(salaModificada.getNumSala());
	            }
	            if (salaModificada.getCapacidad() != null) {
	                salaExistente.setCapacidad(salaModificada.getCapacidad());
	            }
	            if (salaModificada.getTipoPantalla() != null) {
	                salaExistente.setTipoPantalla(salaModificada.getTipoPantalla());
	            }
	            
	            if (salaModificada.getCine() != null) {
	                salaExistente.setCine(salaModificada.getCine());
	            }
	            
	            return repo.save(salaExistente);
	        })
	        .orElseThrow(() -> new RuntimeException("No se encontró la sala con ID: " + salaModificada.getIdSala()));
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}
	
	public List<SalaDTO> listarSalasPorIdCine(int idCine) {
	    List<Sala> salas = repo.findByCineIdCine(idCine);
	    
	    return salas.stream().map(sala -> {
	        SalaDTO dto = new SalaDTO();
	        dto.setIdSala(sala.getIdSala());
	        dto.setNumSala(sala.getNumSala());
	        dto.setCapacidad(sala.getCapacidad());
	        dto.setTipoPantalla(sala.getTipoPantalla());
	        dto.setIdCine(sala.getCine().getIdCine());
	        return dto;
	    }).toList();
	}
}
