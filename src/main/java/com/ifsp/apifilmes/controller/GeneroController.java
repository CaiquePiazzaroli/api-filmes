package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.model.Genero;
import com.ifsp.apifilmes.service.GeneroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @PostMapping
    public Genero salvarGenero(@RequestBody Genero genero) {
        return generoService.salvarGenero(genero);
    }

    @GetMapping
    public List<Genero> listarGeneros() {
        return generoService.listarGeneros();
    }

    @GetMapping("/{id}")
    public Genero listarGeneroPorId(@PathVariable Integer id){
        return generoService.listarGeneroPorId(id);
    }

    @PutMapping("/{id}")
    public Genero atualizarGenero(@PathVariable Integer id, @RequestBody Genero genero) {
        return generoService.atualizarGenero(id, genero);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarGenero(@PathVariable Integer id) {
        generoService.deletarGenero(id);
        return ResponseEntity.noContent().build();
    }

}
