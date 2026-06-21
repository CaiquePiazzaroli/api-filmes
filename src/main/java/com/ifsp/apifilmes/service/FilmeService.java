package com.ifsp.apifilmes.service;

import com.ifsp.apifilmes.dto.FilmeRequestDTO;
import com.ifsp.apifilmes.exeption.EntidadeNaoEncontrdaException;
import com.ifsp.apifilmes.model.Ator;
import com.ifsp.apifilmes.model.Diretor;
import com.ifsp.apifilmes.model.Filme;
import com.ifsp.apifilmes.model.Genero;
import com.ifsp.apifilmes.repository.FilmeRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {
    private final FilmeRepository filmeRepository;
    private final GeneroService generoService;
    private final DiretorService diretorService;
    private final AtorService atorService;

    public FilmeService(FilmeRepository filmeRepository, GeneroService generoService, DiretorService diretorService, AtorService atorService) {
        this.filmeRepository = filmeRepository;
        this.generoService = generoService;
        this.diretorService = diretorService;
        this.atorService = atorService;
    }

    private Filme montarFilme(FilmeRequestDTO filmeRequestDTO) {
        Filme filme = new Filme();

        filme.setTitulo(filmeRequestDTO.titulo());
        filme.setSnopse(filmeRequestDTO.sinopse());
        filme.setDataLancamento(filmeRequestDTO.dataLancamento());

        Genero genero = generoService.listarGeneroPorId(filmeRequestDTO.generoId());
        filme.setGenero(genero);

        Diretor diretor = diretorService.listarDiretorPorId(filmeRequestDTO.diretorId());
        filme.setDiretor(diretor);

        List<Integer> listaAtoresIds = filmeRequestDTO.atoresIds();
        List<Ator> atores = atorService.listarAtoresPorIds(listaAtoresIds);
        filme.setAtores(atores);

        filme.setDataLancamento(filmeRequestDTO.dataLancamento());

        return filme;
    }

    public Filme salvarFilme(FilmeRequestDTO filmeRequestDTO) {
        Filme filmeMontado = montarFilme(filmeRequestDTO);
        return filmeRepository.save(filmeMontado);
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.findAll();
    }

    public Filme listarFilmePorId(Integer id) {
        Optional<Filme> filme = filmeRepository.findById(id);
        if(filme.isEmpty()) {
            throw new EntidadeNaoEncontrdaException("O filme de id" + id + " nao foi encontrado na base.", "Recurso nao encontrado");
        }
        return filme.get();
    }

    public Filme atualizarFilme(FilmeRequestDTO filmeRequestDTO, Integer id) {
        Filme filme = listarFilmePorId(id);

        filme.setTitulo(filmeRequestDTO.titulo());
        filme.setSnopse(filmeRequestDTO.sinopse());
        filme.setDataLancamento(filmeRequestDTO.dataLancamento());

        Genero genero = generoService.listarGeneroPorId(filmeRequestDTO.generoId());
        filme.setGenero(genero);

        Diretor diretor = diretorService.listarDiretorPorId(filmeRequestDTO.diretorId());
        filme.setDiretor(diretor);

        List<Ator> atores = atorService.listarAtoresPorIds(filmeRequestDTO.atoresIds());
        filme.setAtores(atores);

        return filmeRepository.save(filme);
    }

    public void deletarFilme(Integer id) {
        Filme filme = listarFilmePorId(id);
        filmeRepository.deleteById(filme.getId());
    }

}
