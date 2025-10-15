package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.repository.FilmeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<Filme> listar() {
        return filmeRepository.findAll();
    }


    public Filme buscarPorId(String id) {
        UUID uuid = UUID.fromString(id);
        return filmeRepository.findById(uuid)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme n o encontrado com o ID: " + id));
    }


    public Filme salvar(Filme filme) {
        return filmeRepository.save(filme);
    }

    public Filme atualizar(String id, Filme filme) {
        UUID uuid = UUID.fromString(id);
        filmeRepository.findById(uuid);
        return filmeRepository.save(filme);
    }


    public void remover(String id) {
        UUID uuid = UUID.fromString(id);
        Optional<Filme> filmeOptional = filmeRepository.findById(uuid);
        filmeOptional.ifPresentOrElse(
                filmeRepository::delete, () -> new  EntidadeNaoEncontradaException("filme não encontrado!"));

    }
}