package com.uniesp.backflix.demo.service;


import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.model.MinhaLista;
import com.uniesp.backflix.demo.model.Serie;
import com.uniesp.backflix.demo.model.Usuario;
import com.uniesp.backflix.demo.repository.FilmeRepository;
import com.uniesp.backflix.demo.repository.MinhaListaRepository;
import com.uniesp.backflix.demo.repository.SerieRepository;
import com.uniesp.backflix.demo.repository.UsuarioRepository;
import com.uniesp.backflix.demo.service.dtos.MinhaListaRequestDTO;
import com.uniesp.backflix.demo.service.dtos.MinhaListaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MinhaListaService {

    private final MinhaListaRepository minhaListaRepository;
    private final FilmeRepository filmeRepository;
    private final SerieRepository serieRepository;
    private final UsuarioRepository usuarioRepository;

    public MinhaListaResponseDTO adicionar(MinhaListaRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Filme filme = null;
        Serie serie = null;

        if (dto.getIdFilme() != null)
            filme = filmeRepository.findById(dto.getIdFilme())
                    .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        if (dto.getIdSerie() != null)
            serie = serieRepository.findById(dto.getIdSerie())
                    .orElseThrow(() -> new RuntimeException("Série não encontrada"));

        MinhaLista entity = MinhaLista.builder()
                .usuario(usuario)
                .filme(filme)
                .serie(serie)
                .build();

        MinhaLista salvo = minhaListaRepository.save(entity);

        return MinhaListaResponseDTO.builder()
                .idMinhaLista(salvo.getIdMinhaLista())
                .tituloFilme(filme != null ? filme.getTitulo() : null)
                .tituloSerie(serie != null ? serie.getTitulo() : null)
                .nomeUsuario(usuario.getNome())
                .build();
    }

    public List<MinhaListaResponseDTO> listar() {
        return minhaListaRepository.findAll().stream()
                .map(m -> MinhaListaResponseDTO.builder()
                        .idMinhaLista(m.getIdMinhaLista())
                        .tituloFilme(m.getFilme() != null ? m.getFilme().getTitulo() : null)
                        .tituloSerie(m.getSerie() != null ? m.getSerie().getTitulo() : null)
                        .nomeUsuario(m.getUsuario().getNome())
                        .build())
                .collect(Collectors.toList());
    }

    public void remover(Long id) {
        minhaListaRepository.deleteById(id);
    }
}
