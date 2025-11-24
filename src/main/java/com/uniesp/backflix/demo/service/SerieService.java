package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.repository.SerieRepository;
import com.uniesp.backflix.demo.service.dtos.GeneroDTO;
import com.uniesp.backflix.demo.service.dtos.SerieRequest;
import com.uniesp.backflix.demo.service.dtos.SerieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SerieService {

    private final SerieRepository repository;

    public SerieResponse toResponse(Serie s) {
        return new SerieResponse(
                s.getId(),
                s.getTitulo(),
                s.getSinopse(),
                s.getNota(),
                s.getDataLancamento(),
                s.getQuantidadeTemporadas(),
                s.getQuantidadeEpisodios(),
                s.getClassificacaoIdade(),
                new GeneroDTO(s.getGenero().getId(), s.getGenero().getGenero())
        );
    }

    public Serie toEntity(SerieRequest req, Genero genero) {
        return Serie.builder()
                .titulo(req.getTitulo())
                .sinopse(req.getSinopse())
                .nota(req.getNota())
                .dataLancamento(req.getDataLancamento())
                .quantidadeTemporadas(req.getQuantidadeTemporadas())
                .quantidadeEpisodios(req.getQuantidadeEpisodios())
                .classificacaoIdade(req.getClassificacaoIdade())
                .genero(genero)
                .build();
    }

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
        Serie existente = buscar(id);

        if (serie.getTitulo() != null) existente.setTitulo(serie.getTitulo());
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
        Serie serie = buscar(id);
        repository.delete(serie);
    }
}
