package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Integer> {
	List<Sala> findByCineIdCine(int idCine);
}
