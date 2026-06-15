package com.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Pelicula;

public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {

}
