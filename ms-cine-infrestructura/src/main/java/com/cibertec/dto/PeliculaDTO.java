package com.cibertec.dto;

import lombok.Data;

@Data
public class PeliculaDTO {
	private Integer idPeli;
	private String nombrePeli;
	private String categoria;
	private String clasificacion;
	private String calificacion;
	private String anio;
}