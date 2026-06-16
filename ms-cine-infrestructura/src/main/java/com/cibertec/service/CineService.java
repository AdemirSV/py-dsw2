package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Cine;
import com.cibertec.repository.CineRepository;

@Service
public class CineService {
	
	@Autowired
	private CineRepository repo;
	
	public List<Cine> lstCine(){
		return repo.findAll();
	}

	public Optional<Cine> buscarCinePorId(int id){
		return repo.findById(id);
	}
	
	public Cine registrar(Cine cine) {
		return repo.save(cine);
	}
	
	public Cine actualizar(Cine cineModificado) {
		return repo.findById(cineModificado.getIdCine())
			.map(cineExistente -> {
				if (cineModificado.getNombreCine() != null) {
					cineExistente.setNombreCine(cineModificado.getNombreCine());
				}
				if (cineModificado.getDireccion() != null) {
					cineExistente.setDireccion(cineModificado.getDireccion());
				}
				if (cineModificado.getCiudad() != null) {
					cineExistente.setCiudad(cineModificado.getCiudad());
				}
				
				return repo.save(cineExistente);
			})
			.orElseThrow(() -> new RuntimeException("No se encontró el cine con ID: " + cineModificado.getIdCine()));
	}
	
	public void eliminar(int id) {
		repo.deleteById(id);
	}
}
