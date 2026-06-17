package com.ifsp.apifilmes.repository;

import com.ifsp.apifilmes.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme, Integer> {
}
