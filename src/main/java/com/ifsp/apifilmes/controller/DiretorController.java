package com.ifsp.apifilmes.controller;

import com.ifsp.apifilmes.model.Diretor;
import com.ifsp.apifilmes.service.DiretorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("diretores")
public class DiretorController {

    private final DiretorService diretorService;

    public DiretorController(DiretorService diretorService) {
        this.diretorService = diretorService;
    }

    @PostMapping
    public Diretor salvarDiretor(@RequestBody Diretor diretor) {
        return diretorService.salvarDiretor(diretor);
    }

    @GetMapping
    public List<Diretor> listarDiretores() {
        return diretorService.listarDiretores();
    }

    @GetMapping("/{id}")
    public Diretor listarDiretorPorId(@PathVariable Integer id){
        return diretorService.listarDiretorPorId(id);
    }

    @PutMapping("/{id}")
    public Diretor atualizarDiretor(@PathVariable Integer id, @RequestBody Diretor diretor) {
        return diretorService.atualizarDiretor(id, diretor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiretor(@PathVariable Integer id) {
        diretorService.deletarDiretor(id);
        return ResponseEntity.noContent().build();
    }
}
