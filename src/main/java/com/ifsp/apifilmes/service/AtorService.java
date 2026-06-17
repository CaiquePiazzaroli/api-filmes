package com.ifsp.apifilmes.service;

import com.ifsp.apifilmes.exeption.EntidadeDuplicadaException;
import com.ifsp.apifilmes.exeption.EntidadeNaoEncontrdaException;
import com.ifsp.apifilmes.model.Ator;
import com.ifsp.apifilmes.repository.AtorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtorService {

    private final AtorRepository atorRepository;

    public AtorService(AtorRepository atorRepository) {
        this.atorRepository = atorRepository;
    }

    public Ator salvarAtor(Ator ator) {
        Optional<Ator> atorExistente = atorRepository.findByNome(ator.getNome());

        if(atorExistente.isPresent()) {
            throw new EntidadeDuplicadaException("Não é possível salvar um ator que ja existe", "Entidade duplicada");
        }

        return atorRepository.save(ator);
    }

    public List<Ator> listarAtores() {
        return atorRepository.findAll();
    }

    public Ator listarAtorPorId(Integer id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrdaException("Recuso não encontrado", "O id " + id + " não foi encontrado na base de dados"));
    }

    public Ator atualizarAtor(Integer id, Ator novoAtor) {
        Ator ator = listarAtorPorId(id);

        if(!ator.getNome().equalsIgnoreCase(novoAtor.getNome())) {
            if(atorRepository.findByNome(novoAtor.getNome()).isPresent()) {
                throw new EntidadeDuplicadaException("O ator " + novoAtor.getNome() + " ja está cadastrado", "Cadastro duplicado");
            }
        }

        ator.setNome(novoAtor.getNome());
        ator.setDataNascimento(novoAtor.getDataNascimento());
        ator.setFilmes(ator.getFilmes());

        return atorRepository.save(ator);
    }

    public void deletarAtor(Integer id) {
        Ator ator = listarAtorPorId(id);
        atorRepository.deleteById(ator.getId());
    }

    public List<Ator> listarAtoresPorIds(List<Integer> ids) {
        return atorRepository.findAllById(ids);
    }

}
