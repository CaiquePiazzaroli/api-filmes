package com.ifsp.apifilmes.service;

import com.ifsp.apifilmes.exeption.EntidadeDuplicadaException;
import com.ifsp.apifilmes.exeption.EntidadeNaoEncontrdaException;
import com.ifsp.apifilmes.model.Genero;
import com.ifsp.apifilmes.repository.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero salvarGenero(Genero genero) {
        Optional<Genero> generoExistente = generoRepository.findByDescricao(genero.getDescricao());

        if(generoExistente.isPresent()) {
           throw new EntidadeDuplicadaException("Não é possível salvar um gênero que ja existe", "Entidade duplicada");
        }

        return generoRepository.save(genero);
    }

    public List<Genero> listarGeneros() {
        return generoRepository.findAll();
    }

    public Genero listarGeneroPorId(Integer id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrdaException("Recuso não encontrado", "O id " + id + " não foi encontrado na base de dados"));
    }

    public Genero atualizarGenero(Integer id, Genero novoGenero) {
        Genero genero = listarGeneroPorId(id);
        if(!genero.getDescricao().equalsIgnoreCase(novoGenero.getDescricao())) {

            genero.setDescricao(novoGenero.getDescricao());
            return salvarGenero(genero);

        }
        return genero;
    }

    public void deletarGenero(Integer id) {
        Genero genero = listarGeneroPorId(id);
        generoRepository.deleteById(genero.getId());
    }
}
