package com.cibertec.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="sala")
@AllArgsConstructor
@NoArgsConstructor
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSala;
	private String numSala;
	private Integer capacidad;
	private String tipoPantalla;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_cine",nullable = false)
	private Cine cine;
}
