package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.repository.FilmeRepository;
import com.uniesp.backflix.demo.service.dtos.FilmeRequest;
import com.uniesp.backflix.demo.service.dtos.FilmeResponse;
import com.uniesp.backflix.demo.service.dtos.GeneroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;
    private final GeneroService generoService;

    public List<FilmeResponse> listarResponses() {
        return filmeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public FilmeResponse buscarResponse(String id) {
        Filme filme = buscarPorId(id);
        return toResponse(filme);
    }

    public Filme salvarFromRequest(FilmeRequest request) {
        Filme filme = toEntity(request);
        return filmeRepository.save(filme);
    }

    public FilmeResponse salvar(FilmeRequest request) {
        Filme f = salvarFromRequest(request);
        return toResponse(f);
    }

    public FilmeResponse atualizar(String id, FilmeRequest request) {
        Filme existente = buscarPorId(id);
        atualizarCampos(existente, request);
        Filme atualizado = filmeRepository.save(existente);
        return toResponse(atualizado);
    }

    public void remover(String id) {
        Filme f = buscarPorId(id);
        filmeRepository.delete(f);
    }

    private Filme toEntity(FilmeRequest request) {
        return Filme.builder()
                .titulo(request.getTitulo())
                .sinopse(request.getSinopse())
                .nota(request.getNota())
                .dataLancamento(request.getDataLancamento())
                .duracaoMinutos(request.getDuracaoMinutos())
                .classificacaoIndicativa(request.getClassificacaoIndicativa())
                .genero(generoService.buscarEntidadePorId(request.getGeneroId()))
                .build();
    }

    private FilmeResponse toResponse(Filme filme) {
        return new FilmeResponse(
                filme.getId(),
                filme.getTitulo(),
                filme.getSinopse(),
                filme.getNota(),
                filme.getDataLancamento(),
                filme.getDuracaoMinutos(),
                filme.getClassificacaoIndicativa(),
                new GeneroDTO(
                        filme.getGenero().getId(),
                        filme.getGenero().getGenero()
                )
        );
    }

    private void atualizarCampos(Filme existente, FilmeRequest request) {
        existente.setTitulo(request.getTitulo());
        existente.setSinopse(request.getSinopse());
        existente.setNota(request.getNota());
        existente.setDataLancamento(request.getDataLancamento());
        existente.setDuracaoMinutos(request.getDuracaoMinutos());
        existente.setClassificacaoIndicativa(request.getClassificacaoIndicativa());
        existente.setGenero(
                generoService.buscarEntidadePorId(request.getGeneroId())
        );
    }

    public Filme buscarPorId(String id) {
        return filmeRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado!"));
    }
}