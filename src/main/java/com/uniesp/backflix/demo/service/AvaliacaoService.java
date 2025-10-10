package com.uniesp.backflix.demo.service;


import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;

    public List<Avaliacao> listar() {return repository.findAll(); }

    public Avaliacao salvar(Avaliacao avaliacao){

        return repository.save(avaliacao);
    }

    public Avaliacao buscar (String id){

        return repository.findById(UUID.fromString(id)).orElseThrow(() ->new EntidadeNaoEncontradaException("Avaliação não encontrata!"));
    }

    public Avaliacao atualizar(String id, Avaliacao avaliacao) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Avaliação não encontrada!"));
    }
    public void deletar(String id) {
        repository.findById(UUID.fromString(id)).orElseThrow(() -> new EntidadeNaoEncontradaException("Avaliação não encontrada!"));
    }



}
