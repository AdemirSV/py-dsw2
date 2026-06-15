package com.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cibertec.model.Pelicula;
import com.cibertec.repository.PeliculaRepository;

@Service
public class PeliculaService {
	
	@Autowired
	private PeliculaRepository repo;
	
	public List<Pelicula> lstPeliculas(){
		return repo.findAll();
	}
	
	public Optional<Pelicula> buscarPeliPorId(int id){
		return repo.findById(id);
	}
	
	public Pelicula registrar(Pelicula peli) {
		return repo.save(peli);
	}
	
	public Pelicula actualizar(Pelicula peli) {
		return repo.save(peli);
	}

	public void eliminar(int id) {
		repo.deleteById(id);
	}

}
