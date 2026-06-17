package com.ifsp.apifilmes.repository;

import com.ifsp.apifilmes.model.Ator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AtorRepository extends JpaRepository<Ator, Integer> {
    Optional<Ator> findByNome(String nome);
}
