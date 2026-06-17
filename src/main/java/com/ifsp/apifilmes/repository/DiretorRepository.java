package com.ifsp.apifilmes.repository;

import com.ifsp.apifilmes.model.Diretor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiretorRepository extends JpaRepository<Diretor, Integer> {
    Optional<Diretor> findByNome(String nome);
}
