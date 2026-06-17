package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.dto.FilmeRequestDTO;
import com.ifsp.apifilmes.model.Filme;
import com.ifsp.apifilmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService filmeService;

    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    public ResponseEntity<Filme> salvarFilme(@RequestBody FilmeRequestDTO filmeRequestDTO) {
        Filme filme = filmeService.salvarFilme(filmeRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(filme);
    }

}
