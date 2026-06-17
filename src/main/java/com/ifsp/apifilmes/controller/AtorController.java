package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.model.Ator;
import com.ifsp.apifilmes.service.AtorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atores")
public class AtorController {
    private final AtorService atorService;

    public AtorController(AtorService atorService) {
        this.atorService = atorService;
    }

    @PostMapping
    public Ator salvarAtor(@RequestBody Ator ator) {
        return atorService.salvarAtor(ator);
    }

    @GetMapping
    public List<Ator> listarAtores() {
        return atorService.listarAtores();
    }

    @GetMapping("/{id}")
    public Ator listarAtorPorId(@PathVariable Integer id){
        return atorService.listarAtorPorId(id);
    }

    @PutMapping("/{id}")
    public Ator atualizarAtor(@PathVariable Integer id, @RequestBody Ator ator) {
        return atorService.atualizarAtor(id, ator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAtor(@PathVariable Integer id) {
        atorService.deletarAtor(id);
        return ResponseEntity.noContent().build();
    }
}
