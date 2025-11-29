package com.uniesp.backflix.demo.service;

import com.uniesp.backflix.demo.exception.EntidadeNaoEncontradaException;
import com.uniesp.backflix.demo.model.Avaliacao;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.model.Usuario;
import com.uniesp.backflix.demo.repository.*;
import com.uniesp.backflix.demo.service.dtos.AvaliacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {

    private final AvaliacaoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;

    public List<Avaliacao> listar() {
        return repository.findAll();
    }

    public Avaliacao salvar(AvaliacaoRequestDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado"));

        Filme filme = null;
        Serie serie = null;

        if (dto.getFilmeId() != null) {
            filme = filmeRepository.findById(dto.getFilmeId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado"));
        }

        if (dto.getSerieId() != null) {
            serie = serieRepository.findById(dto.getSerieId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Série não encontrada"));
        }

        Avaliacao avaliacao = Avaliacao.builder()
                .usuario(usuario)
                .filme(filme)
                .serie(serie)
                .comentario(dto.getComentario())
                .nota(dto.getNota())
                .build();

        return repository.save(avaliacao);
    }

    public Avaliacao buscar(String id) {
        return repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Avaliação não encontrada!"));
    }

    public Avaliacao atualizar(String id, AvaliacaoRequestDTO dto) {

        Avaliacao existente = buscar(id);

        existente.setComentario(dto.getComentario());
        existente.setNota(dto.getNota());

        if (dto.getFilmeId() != null) {
            Filme filme = filmeRepository.findById(dto.getFilmeId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Filme não encontrado"));
            existente.setFilme(filme);
        }

        if (dto.getSerieId() != null) {
            Serie serie = serieRepository.findById(dto.getSerieId())
                    .orElseThrow(() -> new EntidadeNaoEncontradaException("Série não encontrada"));
            existente.setSerie(serie);
        }

        return repository.save(existente);
    }

    public void deletar(String id) {
        Avaliacao existente = buscar(id);
        repository.delete(existente);
    }
}
