package com.cibertec.model;

import org.hibernate.annotations.DynamicUpdate;

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

@Entity
@DynamicUpdate
@Data
@Table(name="asiento")
@AllArgsConstructor
@NoArgsConstructor
public class Asiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAsiento;
	private String fila;
	private Integer numero;	
	private String tipoAsiento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_sala" , nullable = false)
	private Sala sala;
	

}
