package com.uniesp.backflix.demo.service;


import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.repository.GeneroRepository;
import com.uniesp.backflix.demo.service.dtos.GeneroRequest;
import com.uniesp.backflix.demo.service.dtos.GeneroResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<GeneroResponse> listar() {
        return generoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public GeneroResponse buscarPorId(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
        return toResponse(genero);
    }

    public GeneroResponse salvar(GeneroRequest request) {
        Genero genero = new Genero();
        genero.setGenero(request.getGenero());
        Genero salvo = generoRepository.save(genero);
        return toResponse(salvo);
    }

    public GeneroResponse atualizar(Long id, GeneroRequest request) {
        Genero generoExistente = generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));

        generoExistente.setGenero(request.getGenero());
        Genero atualizado = generoRepository.save(generoExistente);

        return toResponse(atualizado);
    }

    public void remover(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
        generoRepository.delete(genero);
    }

    private GeneroResponse toResponse(Genero genero) {
        return new GeneroResponse(genero.getId(), genero.getGenero());
    }

    public Genero buscarEntidadePorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
    }
}