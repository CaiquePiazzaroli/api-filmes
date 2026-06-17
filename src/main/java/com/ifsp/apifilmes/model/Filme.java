package com.ifsp.apifilmes.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;
    private LocalDate dataLancamento;
    private String snopse;

    @ManyToOne
    @JoinColumn(name = "diretor_id")
    Diretor diretor;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    Genero genero;

    @JsonManagedReference
    @ManyToMany
    @JoinTable(
            name = "atores_filmes",
            joinColumns = @JoinColumn(name = "filme_id"),
            inverseJoinColumns = @JoinColumn(name = "ator_id")
    )
    List<Ator> atores;

    public Filme() {
    }

    public Filme(Integer id, String titulo, LocalDate dataLancamento, String snopse, Diretor diretor, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.dataLancamento = dataLancamento;
        this.snopse = snopse;
        this.diretor = diretor;
        this.genero = genero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getSnopse() {
        return snopse;
    }

    public void setSnopse(String snopse) {
        this.snopse = snopse;
    }

    public Diretor getDiretor() {
        return diretor;
    }

    public void setDiretor(Diretor diretor) {
        this.diretor = diretor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public List<Ator> getAtores() {
        return atores;
    }

    public void setAtores(List<Ator> atores) {
        this.atores = atores;
    }
}
