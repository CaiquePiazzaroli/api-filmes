package com.ifsp.apifilmes.service;

import com.ifsp.apifilmes.exeption.EntidadeDuplicadaException;
import com.ifsp.apifilmes.exeption.EntidadeNaoEncontrdaException;
import com.ifsp.apifilmes.model.Diretor;
import com.ifsp.apifilmes.repository.DiretorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DiretorService {

    private DiretorRepository diretorRepository;

    public DiretorService(DiretorRepository diretorRepository) {
        this.diretorRepository = diretorRepository;
    }

    public Diretor salvarDiretor(Diretor diretor) {
        Optional<Diretor> diretorExistente = diretorRepository.findByNome(diretor.getNome());

        if(diretorExistente.isPresent()) {
            throw new EntidadeDuplicadaException("Não é possível salvar um diretor que ja existe", "Entidade duplicada");
        }

        return diretorRepository.save(diretor);
    }

    public List<Diretor> listarDiretores() {
        return diretorRepository.findAll();
    }

    public Diretor listarDiretorPorId(Integer id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontrdaException("Recuso não encontrado", "O id " + id + " não foi encontrado na base de dados"));
    }

    public Diretor atualizarDiretor(Integer id, Diretor novoDiretor) {
        Diretor diretor = listarDiretorPorId(id);

        if(!diretor.getNome().equalsIgnoreCase(novoDiretor.getNome())) {
            if(diretorRepository.findByNome(novoDiretor.getNome()).isPresent()) {
                throw new EntidadeDuplicadaException("O diretor " + novoDiretor.getNome() + " ja está cadastrado", "Cadastro duplicado");
            }
        }

        diretor.setNome(novoDiretor.getNome());
        diretor.setDataNascimento(novoDiretor.getDataNascimento());

        return diretorRepository.save(diretor);
    }

    public void deletarDiretor(Integer id) {
        Diretor diretor = listarDiretorPorId(id);
        diretorRepository.deleteById(diretor.getId());
    }


}
