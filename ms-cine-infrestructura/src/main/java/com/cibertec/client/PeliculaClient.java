package com.cibertec.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cibertec.dto.PeliculaDTO;

@FeignClient(name = "ms-cine-pelicula", url = "http://localhost:8081/cine/peliculas")
public interface PeliculaClient {

    @GetMapping("/{id}")
    PeliculaDTO buscarPeliPorId(@PathVariable("id") int id);
}