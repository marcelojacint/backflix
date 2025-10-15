package com.uniesp.backflix.demo.service;


import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.repository.GeneroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneroService {

    private final GeneroRepository generoRepository;

    public List<Genero> listar() {
        return generoRepository.findAll();
    }

    public Genero buscarPorId(Long id) {
        return generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));
    }

    public Genero salvar(Genero genero) {
        return generoRepository.save(genero);
    }

    public Genero atualizar(Long id, Genero genero) {
        Genero generoExistente = generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado com o ID: " + id));

        genero.setId(generoExistente.getId());
        return generoRepository.save(genero);
    }

    public void remover(Long id) {
        Genero genero = generoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Gênero não encontrado!"));
        generoRepository.delete(genero);
    }
}
