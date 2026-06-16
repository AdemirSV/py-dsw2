package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.dto.AsientoDTO;
import com.cibertec.model.Asiento;
import com.cibertec.repository.AsientoRepository;

@Service
public class AsientoService {

	@Autowired
	private AsientoRepository repo;
	
	public List<Asiento> lstAsiento(){
		return repo.findAll();
	}

	public Optional<Asiento> buscarAsientoPorId(int id){
		return repo.findById(id);
	}
	
	public Asiento registrar(Asiento asiento) {
		return repo.save(asiento);
	}
	
	public Asiento actualizar(Asiento asientoModificado) {
	    return repo.findById(asientoModificado.getIdAsiento())
	        .map(asientoExistente -> {
	            if (asientoModificado.getFila() != null) {
	                asientoExistente.setFila(asientoModificado.getFila());
	            }
	            if (asientoModificado.getNumero() != null) {
	                asientoExistente.setNumero(asientoModificado.getNumero());
	            }
	            if (asientoModificado.getTipoAsiento() != null) {
	                asientoExistente.setTipoAsiento(asientoModificado.getTipoAsiento());
	            }
	            
	            if (asientoModificado.getSala() != null) {
	                asientoExistente.setSala(asientoModificado.getSala());
	            }
	            
	            return repo.save(asientoExistente);
	        })
	        .orElseThrow(() -> new RuntimeException("No se encontró el asiento con ID: " + asientoModificado.getIdAsiento()));
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}
	
	public List<AsientoDTO> listarAsientosPorIdSala(int idSala) {
        List<Asiento> asientos = repo.findBySalaIdSala(idSala);
        
        return asientos.stream().map(asiento -> {
            AsientoDTO dto = new AsientoDTO();
            dto.setIdAsiento(asiento.getIdAsiento());
            dto.setFila(asiento.getFila());
            dto.setNumero(asiento.getNumero());
            dto.setTipoAsiento(asiento.getTipoAsiento());
            dto.setIdSala(asiento.getSala().getIdSala());
            return dto;
        }).toList();
    }
}
