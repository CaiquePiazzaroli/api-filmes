package com.ifsp.apifilmes.repository;

import com.ifsp.apifilmes.model.Genero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneroRepository extends JpaRepository <Genero, Integer>{
    Optional<Genero> findByDescricao(String descricao);
}
