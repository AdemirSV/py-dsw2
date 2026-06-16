package com.cibertec.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="cine")
@NoArgsConstructor
@AllArgsConstructor
public class Cine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCine;
	
	private String nombreCine;
    private String direccion;
    private String ciudad;

    @OneToMany(mappedBy = "cine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore 
    private List<Sala> salas;

}
