package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.repository.SerieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository repository;

    public List<Serie> listar() {
        return repository.findAll();
    }

    public Serie salvar(Serie serie) {
        return repository.save(serie);
    }

    public Serie buscar(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Série não encontrada!"));
    }

    public Serie atualizar(String id, Serie serie) {
        Serie existente = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Série não encontrada"));

        if (serie.getTituloSerie() != null) existente.setTituloSerie(serie.getTituloSerie());
        if (serie.getSinopse() != null) existente.setSinopse(serie.getSinopse());
        if (serie.getNota() != null) existente.setNota(serie.getNota());
        if (serie.getDataLancamento() != null) existente.setDataLancamento(serie.getDataLancamento());
        if (serie.getQuantidadeTemporadas() != null) existente.setQuantidadeTemporadas(serie.getQuantidadeTemporadas());
        if (serie.getQuantidadeEpisodios() != null) existente.setQuantidadeEpisodios(serie.getQuantidadeEpisodios());
        if (serie.getGenero() != null) existente.setGenero(serie.getGenero());
        if (serie.getClassificacaoIdade() != null) existente.setClassificacaoIdade(serie.getClassificacaoIdade());

        return repository.save(existente);
    }

    public void deletar(String id) {
        repository.findById(UUID.fromString(id))
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new EntidadeNaoEncontradaException("Série não encontrada!");
                        }
                );
    }
}
