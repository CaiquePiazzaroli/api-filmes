package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.dto.FilmeRequestDTO;
import com.ifsp.apifilmes.model.Filme;
import com.ifsp.apifilmes.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Filme>> listarFilmes() {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.listarFilmes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> listarFilmePorId(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.listarFilmePorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> atualizarFilme(@RequestBody FilmeRequestDTO filmeRequestDTO, @PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(filmeService.atualizarFilme(filmeRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarFilme(@PathVariable Integer id) {
        filmeService.deletarFilme(id);
        return ResponseEntity.noContent().build();
    }
}
