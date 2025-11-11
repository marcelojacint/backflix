package com.uniesp.backflix.demo.controller;

import com.uniesp.backflix.demo.controller.utils.UriUtils;
import com.uniesp.backflix.demo.model.Filme;
import com.uniesp.backflix.demo.model.Genero;
import com.uniesp.backflix.demo.service.FilmeService;
import com.uniesp.backflix.demo.service.GeneroService;
import com.uniesp.backflix.demo.service.dtos.FilmeRequest;
import com.uniesp.backflix.demo.service.dtos.FilmeResponse;
import com.uniesp.backflix.demo.service.dtos.GeneroDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
@RequiredArgsConstructor
public class FilmeController {

    private final FilmeService filmeService;
    private final GeneroService generoService; // necessário para buscar o gênero pelo ID

    @GetMapping
    public ResponseEntity<List<FilmeResponse>> listar() {
        List<FilmeResponse> filmes = filmeService.listar().stream()
                .map(f -> new FilmeResponse(
                        f.getId(),
                        f.getNome(),
                        f.getTitulo(),
                        f.getSinopse(),
                        f.getNota(),
                        f.getDataLancamento(),
                        f.getDuracaoMinutos(),
                        f.getClassificacaoIndicativa(),
                        new GeneroDTO(f.getGenero().getId(), f.getGenero().getGenero())
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(filmes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponse> buscarPorId(@PathVariable String id) {
        Filme f = filmeService.buscarPorId(id);
        FilmeResponse response = new FilmeResponse(
                f.getId(),
                f.getNome(),
                f.getTitulo(),
                f.getSinopse(),
                f.getNota(),
                f.getDataLancamento(),
                f.getDuracaoMinutos(),
                f.getClassificacaoIndicativa(),
                new GeneroDTO(f.getGenero().getId(), f.getGenero().getGenero())
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<FilmeResponse> salvar(@Valid @RequestBody FilmeRequest request) {
        Genero genero = generoService.buscarPorId(request.getGeneroId());

        Filme filme = new Filme();
        filme.setTitulo(request.getTitulo());
        filme.setSinopse(request.getSinopse());
        filme.setNota(request.getNota());
        filme.setDataLancamento(request.getDataLancamento());
        filme.setDuracaoMinutos(request.getDuracaoMinutos());
        filme.setClassificacaoIndicativa(request.getClassificacaoIndicativa());
        filme.setGenero(genero);

        Filme salvo = filmeService.salvar(filme);

        URI uri = UriUtils.criarUriParaRecurso(salvo.getId());
        FilmeResponse response = new FilmeResponse(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTitulo(),
                salvo.getSinopse(),
                salvo.getNota(),
                salvo.getDataLancamento(),
                salvo.getDuracaoMinutos(),
                salvo.getClassificacaoIndicativa(),
                new GeneroDTO(genero.getId(), genero.getGenero())
        );

        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> atualizar(@PathVariable String id, @Valid @RequestBody FilmeRequest request) {
        Genero genero = generoService.buscarPorId(request.getGeneroId());

        Filme filme = new Filme();
        filme.setTitulo(request.getTitulo());
        filme.setSinopse(request.getSinopse());
        filme.setNota(request.getNota());
        filme.setDataLancamento(request.getDataLancamento());
        filme.setDuracaoMinutos(request.getDuracaoMinutos());
        filme.setClassificacaoIndicativa(request.getClassificacaoIndicativa());
        filme.setGenero(genero);

        filmeService.atualizar(id, filme);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable String id) {
        filmeService.remover(id);
        return ResponseEntity.noContent().build();
    }
}