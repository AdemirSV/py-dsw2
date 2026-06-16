package com.cibertec.dto;

import lombok.Data;

@Data
public class AsientoDTO {
    private Integer idAsiento;
    private String fila;
    private Integer numero;
    private String tipoAsiento;
    private Integer idSala;
}