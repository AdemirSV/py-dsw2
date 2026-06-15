package com.cibertec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="pelicula")
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPeli;
	private String nombrePeli;
	private String categoria;
	private String clasificacion;
	private String calificacion;
	private String anio;

}
