package com.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.model.Asiento;

public interface AsientoRepository extends JpaRepository<Asiento, Integer> {
	List<Asiento> findBySalaIdSala(int idSala);
}
